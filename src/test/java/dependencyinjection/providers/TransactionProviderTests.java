package dependencyinjection.providers;

import com.github.mp.dependencyinjection.providers.TransactionProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionProviderTests {
    private TransactionProvider sut;

    @BeforeEach
    public void setUp() {
        sut = new TransactionProvider();
    }

    @Test
    public void tryBeginTransaction_whenTwoTransactionsStarted_OnlyFirstShouldReceiveId() {
        // Arrange

        // Act
        var transactionId = sut.tryBeginTransaction();
        var secondTransactionId = sut.tryBeginTransaction();

        // Assert
        Assertions.assertNotEquals(-1, transactionId);
        Assertions.assertEquals(-1, secondTransactionId);
    }

    @Test
    public void tryFinalizeTransaction_whenTwoTransactionsStarted_OnlyFirstIdShouldStopTransaction() {
        // Arrange

        // Act
        var transactionId = sut.tryBeginTransaction();
        var secondTransactionId = sut.tryBeginTransaction();
        var resultOfSecondId = sut.tryFinalizeTransaction(secondTransactionId);
        var resultOfFirstId = sut.tryFinalizeTransaction(transactionId);

        // Assert
        Assertions.assertTrue(resultOfFirstId);
        Assertions.assertFalse(resultOfSecondId);
    }

    @Test
    public void isTransactionIdValid_whenEmptyIdProvided_shouldReturnFalse() {
        // Arrange

        // Act
        var result = sut.isTransactionIdValid(-1);

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void isTransactionIdValid_whenIdProvidedButTransactionIsNotStarted_shouldReturnFalse() {
        // Arrange

        // Act
        var result = sut.isTransactionIdValid(99);

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void isTransactionIdValid_whenIdProvidedAndTransactionIsStarted_shouldReturnTrue() {
        // Arrange
        var transactionId = sut.tryBeginTransaction();

        // Act
        var result = sut.isTransactionIdValid(transactionId);

        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    public void isTransactionIdValid_whenWrongIdProvidedAndTransactionIsStarted_shouldReturnFalse() {
        // Arrange
        var transactionId = sut.tryBeginTransaction();

        // Act
        var result = sut.isTransactionIdValid(transactionId * 2);

        // Assert
        Assertions.assertFalse(result);
    }
}
