package coe528.project;

/**
 * All required methods are included in the Account interface.
 */
public interface Account {
	
	/** The minimum online purchase value.
        */
	public static final double ONLINE_PURCHASE = 50;

	/**
	 * The balance is incremented by the given value.
	 * 
	 *
	 * @param value the given value
	 * @return true, if successful
	 */
	public boolean deposit(double value);
	
	/**
	 * The balance is decremented by the given value.
	 * 
	 *
	 * @param value the given value
	 * @return true, if successful, otherwise
	 *         false (insufficient funds in account)
	 */
	public boolean withdraw(double value);
	
	/**
	 * Obtains the balance.
	 * @return the balance
	 */
	public double getBalance();
	
	/**
	 * Performs an online purchase. Sums up the fee for silver and gold.
	 * 
	 *
	 * @param value the given value to be withdrawn for purchase
	 * @return true, if successful
	 */
	public boolean doOnlinePurchase(double value);
}
