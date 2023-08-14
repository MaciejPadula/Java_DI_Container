package com.github.mp.dependencyinjection.providers;

import java.util.Random;

public class TransactionProvider implements ITransactionProvider {
    private final int emptyTransactionId = -1;

    private int transactionId = emptyTransactionId;

    public int tryBeginTransaction() {
        if (transactionId == emptyTransactionId) {
            transactionId = new Random().nextInt(0, 100);
            return transactionId;
        }

        return emptyTransactionId;
    }

    public boolean tryFinalizeTransaction(int clientTransactionId) {
        if (transactionId == emptyTransactionId || clientTransactionId != transactionId) {
            return false;
        }

        transactionId = emptyTransactionId;
        return true;
    }
}
