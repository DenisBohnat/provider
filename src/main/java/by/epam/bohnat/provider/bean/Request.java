package by.epam.bohnat.provider.bean;

import java.sql.Date;

/**
 * This bean class describes a user request entity.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class Request {

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
	 * Description of the request
	 */
	private String description;

	/**
	 * Registration date of the request
	 */
	private Date reqDate;

	public Request() {

	}

	public Request(int id, int userId, int tariffId, String description, Date reqDate) {
		this.id = id;
		this.userId = userId;
		this.tariffId = tariffId;
		this.description = description;
		this.reqDate = reqDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((reqDate == null) ? 0 : reqDate.hashCode());
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
		Request other = (Request) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (reqDate == null) {
			if (other.reqDate != null)
				return false;
		} else if (!reqDate.equals(other.reqDate))
			return false;
		if (tariffId != other.tariffId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", userId=" + userId + ", tariffId=" + tariffId + ", description=" + description
				+ ", reqDate=" + reqDate + "]";
	}

}
