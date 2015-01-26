package bank;

public class SavingsAccount extends BankAccount
{
	public double rate;
	
	public SavingsAccount()
	{
		super();
	}
	
	public SavingsAccount(double initialBalance)
	{
		super(initialBalance);
	}
	
	public void addInterest()
	{
		deposit(rate*getBalance());
	}
}
