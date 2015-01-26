package bank;

public abstract class BankAccount
{
	private double balance;
	
	public BankAccount()
	{
		balance = 0;
	}
	
	public BankAccount(double initialBalance)
	{
		balance = initialBalance;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	void withdraw(double amount)
	{
		balance -= amount;
	}

	public void deposit(double amount)
	{
		balance += amount;
	}
}
