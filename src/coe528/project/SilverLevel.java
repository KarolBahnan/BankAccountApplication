package coe528.project;

/**
 * The Class SilverLevel is the subclass of Level
 * This class is immutable since a constant value is returned.
 */
public class SilverLevel extends Level {

	/**
	 * Online fee for silver level is $20.
	 */
	@Override
	public double getOnlineFee() {
		return 20;
	}

	@Override
	public String toString() {
		return Level.SILVER;
	}

}
