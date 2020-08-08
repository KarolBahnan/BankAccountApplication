package coe528.project;

/**
 * The Interface LevelStatus defines 
 * approach to change level of customer.
 */
public interface LevelStatus {

	/**
	 * Sets new level to specified customer
	 *
	 * @param customer the specified customer receiving
	 *        the new level
	 */
	public void setLevel(Customer customer);
}
