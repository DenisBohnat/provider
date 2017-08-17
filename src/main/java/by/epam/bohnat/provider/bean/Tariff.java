package by.epam.bohnat.provider.bean;

/**
 * This bean class describes a tariff entity.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class Tariff {

	/**
	 * Unique identifier
	 */
	private int id;

	/**
	 * Tariff name
	 */
	private String name;

	/**
	 * Tariff type id (1-limited, 2-unlimited)
	 */
	private int type;

	/**
	 * Reception speed
	 */
	private float recSpeed;

	/**
	 * Transmission speed
	 */
	private float transSpeed;

	/**
	 * Subscription fee
	 */
	private float subscriptionFee;

	/**
	 * Traffic volume
	 */
	private int trafficVolume;

	/**
	 * Overdraft amount for spent traffic
	 */
	private float overdraftAmount;

	public Tariff() {

	}

	public Tariff(int id, String name, int type, float recSpeed, float transSpeed, float subscriptionFee,
			int trafficVolume, float overdraftAmount) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.recSpeed = recSpeed;
		this.transSpeed = transSpeed;
		this.subscriptionFee = subscriptionFee;
		this.trafficVolume = trafficVolume;
		this.overdraftAmount = overdraftAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getRecSpeed() {
		return recSpeed;
	}

	public void setRecSpeed(float recSpeed) {
		this.recSpeed = recSpeed;
	}

	public float getTransSpeed() {
		return transSpeed;
	}

	public void setTransSpeed(float transSpeed) {
		this.transSpeed = transSpeed;
	}

	public float getSubscriptionFee() {
		return subscriptionFee;
	}

	public void setSubscriptionFee(float subscriptionFee) {
		this.subscriptionFee = subscriptionFee;
	}

	public int getTrafficVolume() {
		return trafficVolume;
	}

	public void setTrafficVolume(int trafficVolume) {
		this.trafficVolume = trafficVolume;
	}

	public float getOverdraftAmount() {
		return overdraftAmount;
	}

	public void setOverdraftAmount(float overdraftAmount) {
		this.overdraftAmount = overdraftAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(overdraftAmount);
		result = prime * result + Float.floatToIntBits(recSpeed);
		result = prime * result + Float.floatToIntBits(subscriptionFee);
		result = prime * result + trafficVolume;
		result = prime * result + Float.floatToIntBits(transSpeed);
		result = prime * result + type;
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
		Tariff other = (Tariff) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(overdraftAmount) != Float.floatToIntBits(other.overdraftAmount))
			return false;
		if (Float.floatToIntBits(recSpeed) != Float.floatToIntBits(other.recSpeed))
			return false;
		if (Float.floatToIntBits(subscriptionFee) != Float.floatToIntBits(other.subscriptionFee))
			return false;
		if (trafficVolume != other.trafficVolume)
			return false;
		if (Float.floatToIntBits(transSpeed) != Float.floatToIntBits(other.transSpeed))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tariff [id=" + id + ", name=" + name + ", type=" + type + ", recSpeed=" + recSpeed + ", transSpeed="
				+ transSpeed + ", subscriptionFee=" + subscriptionFee + ", trafficVolume=" + trafficVolume
				+ ", overdraftAmount=" + overdraftAmount + "]";
	}

}
