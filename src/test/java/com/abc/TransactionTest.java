package com.abc;

import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertEquals(5.0, t.amount, "The transaction amount should be 5.0");
        assertNotNull(t.transactionDate, "The transaction date should not be null.");
    }
}
