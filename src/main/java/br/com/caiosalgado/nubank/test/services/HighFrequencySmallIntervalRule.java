package br.com.caiosalgado.nubank.test.services;

import br.com.caiosalgado.nubank.test.models.Account;
import br.com.caiosalgado.nubank.test.models.Transaction;
import br.com.caiosalgado.nubank.test.models.TransactionOperation;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HighFrequencySmallIntervalRule implements TransactionRule {

    @Override
    public void validate(Account account, TransactionOperation operation) {
        LocalDateTime validOperationTime = operation.getTransaction().getTime().minusMinutes(2);
        int operationsInsideInterval = 0;
        if(account.getSuccessfulTransactions().size() >= 3) {
            List<Transaction> orderedSuccessfulTransaction = account
                    .getSuccessfulTransactions()
                    .stream()
                    .sorted(Comparator.comparing(Transaction::getTime).reversed())
                    .limit(3)
                    .collect(Collectors.toList());
            System.out.println(orderedSuccessfulTransaction);
            for (Transaction t: orderedSuccessfulTransaction) {
                if(t.getTime().isAfter(validOperationTime)) {
                    operationsInsideInterval ++;
                }
            }
            if(operationsInsideInterval == 3){
                throw new RuntimeException("high-frequency-small-interval");
            }
        }
    }
}
