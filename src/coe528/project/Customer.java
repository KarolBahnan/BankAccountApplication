package coe528.project;

/**
 * The Class Customer, a subclass of User that inherits
 * getUsername() and getPassword() properties.
 * 
 * This class is mutable due to username, level, password
 * and balance being changeable.
 * 
 */
public class Customer extends User implements Account {

	/** Level of customer is defined (Silver, Gold, or Platinum)
	 * According to State design pattern. This references to 
	 * levelStatus interface that applies a specific level to a customer.
         */
	private LevelStatus state;

	/** The balance available currently, in dollars. 
        */
	private double balance;

	/**
	 * Creates a new customer.
	 *
	 * @param username {@link User#getUsername()}
	 * @param password {@link User#getPassword()}
	 */
	public Customer(String username, String password) {
		super(username, password);
	}

	/**
	 * Creates a new customer. This constructor is required
	 * for xml serialization.
	 */
	public Customer() {		
	}

	/**
	 * Gets the state.
	 * @effects returns {@link Customer#state}
	 *
	 * @return the state
	 */
	public LevelStatus getState() {
		return state;
	}

	/**
	 * Sets the state.
	 * @modifies {@link Customer#state}
	 * @effects updated {@link Customer#state}
	 *
	 * @param state the new state
	 */
	public void setState(LevelStatus state) {
		this.state = state;
	}

	/**
	 * @effects returns {@link Role#CUSTOMER}
	 */
	@Override
	public Roles getRoles() {
		return Roles.CUSTOMER;
	}

	/**
	 * @requires value > 0
	 * @modifies {@link Customer#balance}
	 * @effects balance increments
	 */
	@Override
	public boolean deposit(double value) {
		try {
			if (value <= 0) {
				return false;
			}
			balance += value; // Increment balance by value.
			setChanged();
			notifyObservers(); // Notify DefineManager to update file and PresentCustomer to update display.

			return true;
		} finally {
			repOk();
		}
	}

	/**
	 * @requires balance < value
	 * @modifies {@link Customer#balance}
	 * @effects balance decrements
	 */
	@Override
	public boolean withdraw(double value) {
		repOk();
		try {
			if (balance < value) {
				return false;
			}
			balance -= value; // Decrement balance by value.
			setChanged();
			notifyObservers();// Notify DefineManager to update file and PresentCustomer to update display.

			return true;
		} finally {
			repOk();
		}
	}

	/**
	 * @effects returns the current balance ({@link Customer#balance}
	 */
	@Override
	public double getBalance() {
		return balance;
	}

	/**
	 * @requires balance < value and value < {@link Account#MIN_ONLINE_PURCHASE}
	 * @modifies {@link Customer#balance}
	 * @effects balance decrements
	 */
	@Override
	public boolean doOnlinePurchase(double value) {
		repOk();
		try {
			// Begin by checking input value if it is greater or rqual to minimum for purchase.
			if (value < ONLINE_PURCHASE) {
				return false;
			}

			return withdraw(value + ((Level)state).getOnlineFee());
		} finally {
			repOk();
		}
	}

	/**
	 * Sets the balance.
	 * @modifies this
	 * @requires balance >= 0
	 * @effects balance changed
	 *
	 * @param balance the new balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Checks the rep invariant.
	 * @effects nothing if this satisfies rep invariant, 
	 *          otherwise exception is thrown.
	 */
	private void repOk() {
		if (balance < 0) {
			throw new RuntimeException("Balance is negative");
		}
		if (getUsername() == null && getUsername().isEmpty()) {
			throw new RuntimeException("Username box cannot be empty.");
		}
		if (getPassword() == null && getPassword().isEmpty()) {
			throw new RuntimeException("Password box cannot be empty.");
		}
	}

}