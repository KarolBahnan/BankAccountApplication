package coe528.project;

import java.util.Observable;
import java.util.Observer;

/**
 * The Class ManageLevel has a purpose to update
 level of Customer if a change in balance occurs.
 * Since there are 3 final classes, this class is immutable.
 */
public class ManageLevel implements Observer {
	
	/** Silver level. */
	private final SilverLevel silver = new SilverLevel();
	
	/** Gold level. */
	private final GoldLevel gold = new GoldLevel();
	
	/** Platinum level. */
	private final PlatinumLevel platinum = new PlatinumLevel();

	/**
	 * This method updates customer status according to
	 * state pattern if the balance is changed.
	 */
	@Override
	public void update(Observable observe, Object obj) {
		Customer customer = (Customer)observe;
		double bal = customer.getBalance();
		if (bal < 10_000) {
			customer.setState(silver);
		} else if (bal >= 10_000 && bal < 20_000) {
			customer.setState(gold);
		} else {
			customer.setState(platinum);
		}		
	}

}
