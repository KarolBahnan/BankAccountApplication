package coe528.project;

/**
 * The Class Level is an abstract class that
 * specifies the level. It contains the subclasses
 * since every level has a unique fee.
 */
public abstract class Level implements LevelStatus {

	/** The Constant GOLD. */
	protected final static String GOLD = "GOLD";
	
	/** The Constant SILVER. */
	protected final static String SILVER = "SILVER";
	
	/** The Constant PLATINUM. */
	protected final static String PLATINUM = "PLATINUM";

	/**
	 * Gets the online fee.
	 *
	 * @return the online fee
	 */
	public abstract double getOnlineFee();
	
	/**
	 * @requires customer != null
	 * @effects given customer has a new level
	 */
	@Override
	public void setLevel(Customer customer) {
		customer.setState(this);
	}

}
