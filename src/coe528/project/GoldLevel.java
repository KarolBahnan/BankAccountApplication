package coe528.project;

/**
 * The Class GoldLevel is the subclass of Level
 * This class is immutable since a constant value is returned.
 */
public class GoldLevel extends Level {

	/**
	 * Online fee for gold level is $10.
	 */
	@Override
	public double getOnlineFee() {
		return 10;
	}

	@Override
	public String toString() {
		return Level.GOLD;
	}

}
