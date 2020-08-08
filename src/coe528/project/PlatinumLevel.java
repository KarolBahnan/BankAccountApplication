package coe528.project;

/**
 * The Class PlatinumLevel is the subclass of Level
 * This class is immutable since a constant value is returned.
 */
public class PlatinumLevel extends Level {

	/**
         * Online fee for platinum level is $0.
	 */
	@Override
	public double getOnlineFee() {
		return 0;
	}

	@Override
	public String toString() {
		return Level.PLATINUM;
	}

}
