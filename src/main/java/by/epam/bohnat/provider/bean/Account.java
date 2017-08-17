package by.epam.bohnat.provider.bean;

import java.sql.Date;

/**
 * This bean class describes a user account entity.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class Account {

	/**
	 * Unique identifier
	 */
	private int id;

	/**
	 * User id
	 */
	private int userId;

	/**
	 * Tariff id
	 */
	private int tariffId;

	/**
	 * Block id (1-not blocked, 2-blocked)
	 */
	private int block;

	/**
	 * User account number
	 */
	private long accountNumber;

	/**
	 * User amount
	 */
	private float amount;

	/**
	 * Payment date
	 */
	private Date paymentDate;

	/**
	 * Spent traffic, if tariff is limited
	 */
	private int spentTraffic;

	public Account() {

	}

	public Account(int id, int userId, int tariffId, int block, long accountNumber, float amount, Date paymentDate,
			int spentTraffic) {
		this.id = id;
		this.userId = userId;
		this.tariffId = tariffId;
		this.block = block;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.spentTraffic = spentTraffic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTariffId() {
		return tariffId;
	}

	public void setTariffId(int tariffId) {
		this.tariffId = tariffId;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getSpentTraffic() {
		return spentTraffic;
	}

	public void setSpentTraffic(int spentTraffic) {
		this.spentTraffic = spentTraffic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + block;
		result = prime * result + id;
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
		result = prime * result + spentTraffic;
		result = prime * result + tariffId;
		result = prime * result + userId;
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
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (block != other.block)
			return false;
		if (id != other.id)
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		if (spentTraffic != other.spentTraffic)
			return false;
		if (tariffId != other.tariffId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", tariffId=" + tariffId + ", block=" + block
				+ ", accountNumber=" + accountNumber + ", amount=" + amount + ", paymentDate=" + paymentDate
				+ ", spentTraffic=" + spentTraffic + "]";
	}

}
