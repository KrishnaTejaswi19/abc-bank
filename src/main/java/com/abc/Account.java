package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {
 
	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;
	public static final int SUPER_SAVINGS = 3;
	private final int accountType;
	public List<Transaction> transactions;
 
 
	public Account(int accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}
 
 
	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}
 
     public boolean transfer(Account targetAccount, double amount) {
        if (amount <= 0) {
          return false;
        }

        // Check if the source account has enough funds
        if (sumTransactions() < amount) {
         return false;
        }

        // Withdraw from this account (source account)
        this.withdraw(amount);

        // Deposit into the target account
        targetAccount.deposit(amount);
        return true;
    }
 
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount));
		}
	}
 
 
	public double interestEarned() {
		double amount = sumTransactions();
		switch(accountType) {
		case SAVINGS:
			if (amount <= 1000)
				return amount * 0.001;
			else
				return 1 + (amount-1000) * 0.002;
		case MAXI_SAVINGS:
			if (amount <= 1000)
				return amount * 0.02;
			if (amount <= 2000)
				return 20 + (amount-1000) * 0.05;
			return 70 + (amount-2000) * 0.1;
		case SUPER_SAVINGS:
			if (amount <= 1000) {
				return amount * 0.02; 
 
			}
			else if (amount <= 2000) {
return 20 + (amount - 1000) * 0.07; 
 
			}
			else {
  return 90 + (amount - 2000) * 0.12; 
			}
		default:
			return amount * 0.001;
		}
	}
 
 
	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}
 
 
	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction t: transactions)
			amount += t.amount;
		return amount;
	}
 
 
	public int getAccountType() {
		return accountType;
	}
}
