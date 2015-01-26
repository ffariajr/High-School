package bank;

public class CheckingAccount extends BankAccount
{
	private final int FEE = 2;
	private final int MINIMUM_VALUE = 200;

	public CheckingAccount()
	{
		super();
	}
	
	public CheckingAccount(double initialBalance)
	{
		super(initialBalance);
	}
	
	public void withdraw(double amount)
	{
		super.withdraw(amount);
		if(getBalance() > MINIMUM_VALUE)
			super.withdraw(FEE);
	}

}
