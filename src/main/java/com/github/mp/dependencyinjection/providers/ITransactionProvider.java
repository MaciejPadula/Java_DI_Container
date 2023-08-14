package com.github.mp.dependencyinjection.providers;

public interface ITransactionProvider {
    int tryBeginTransaction();
    boolean tryFinalizeTransaction(int transactionId);
    boolean isTransactionIdValid(int transactionId);
}
