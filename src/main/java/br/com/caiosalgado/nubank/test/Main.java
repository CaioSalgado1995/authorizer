package br.com.caiosalgado.nubank.test;

import br.com.caiosalgado.nubank.test.models.*;
import br.com.caiosalgado.nubank.test.services.*;
import br.com.caiosalgado.nubank.test.utils.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // initializing variables
        Account account = null;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        List<TransactionRule> rules = List.of(
                new AccountNotInitializedRule(),
                new CardNotActiveRule(),
                new InsufficientLimitRule(),
                new HighFrequencySmallIntervalRule(),
                new DoubleTransactionRule());
        List<String> violations;

        // reading the file from path passed as argument
        String pathToFile = args[0];
        File file = new File(pathToFile);
        InputStream is = new FileInputStream(file);
        List<String> lines = IOUtils.readLines(is, "UTF-8");

        // processing the file
        for (String line: lines) {
            if(line.startsWith("{\"account")) {
                if (account == null) {
                    AccountOperation accountOperation = gson.fromJson(line, AccountOperation.class);
                    account = new Account(accountOperation.getAccount().isActiveCard(), accountOperation.getAccount().getAvailableLimit());
                    System.out.println(new AccountResponse(account, new ArrayList<>()));
                } else {
                    System.out.println(new AccountResponse(account, List.of("account-already-initialized")));
                    break;
                }
            }

            if(line.startsWith("{\"transaction")) {
                violations = new ArrayList<>();
                TransactionOperation transactionOperation = gson.fromJson(line, TransactionOperation.class);
                for (TransactionRule rule: rules) {
                    try {
                        rule.validate(account, transactionOperation);
                    } catch (RuntimeException ex) {
                        violations.add(ex.getMessage());
                        if(ex.getMessage().equals(AccountNotInitializedRule.ERROR_CODE)) {
                            break;
                        }
                    }
                }
                if(violations.size() == 0) {
                    account.execute(transactionOperation.getTransaction());
                    System.out.println(new AccountResponse(account, new ArrayList<>()));
                }else {
                    System.out.println(new AccountResponse(account, violations));
                }
            }
        }
    }
}
