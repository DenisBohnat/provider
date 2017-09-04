package by.epam.bohnat.provider.bean;

import java.sql.Date;

/**
 * This bean class describes a user payment entity.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class Payment {

	/**
	 * Unique identifier
	 */
	private int id;

	/**
	 * Account id
	 */
	private int accountId;

	/**
	 * Payment date
	 */
	private Date paymentDate;

	/**
	 * Payment amount that the user contributes to the account
	 */
	private float paymentAmount;

	public Payment() {

	}

	public Payment(int id, int accountId, Date paymentDate, float paymentAmount) {
		this.id = id;
		this.accountId = accountId;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public float getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + id;
		result = prime * result + Float.floatToIntBits(paymentAmount);
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (accountId != other.accountId)
			return false;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(paymentAmount) != Float.floatToIntBits(other.paymentAmount))
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", accountId=" + accountId + ", paymentDate=" + paymentDate + ", paymentAmount="
				+ paymentAmount + "]";
	}

}
