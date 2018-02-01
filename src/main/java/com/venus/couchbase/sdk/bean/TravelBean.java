package com.venus.couchbase.sdk.bean;

/**
 * The Class TravelBean.
 */
public class TravelBean {

	/** The callsign. */
	private String callsign;
	
	/** The country. */
	private String country;
	
	/** The iata. */
	private String iata;
	
	/** The icao. */
	private String icao;
	
	/** The id. */
	private String id;
	
	/** The name. */
	private String name;
	
	/** The type. */
	private String type;
	
	/** The city. */
	private String city;
	
	/**
	 * Instantiates a new travel bean.
	 */
	public TravelBean() {}
	
	/**
	 * Instantiates a new travel bean.
	 *
	 * @param callsign the callsign
	 * @param country the country
	 * @param iata the iata
	 * @param icao the icao
	 * @param id the id
	 * @param name the name
	 * @param type the type
	 * @param city the city
	 */
	public TravelBean(String callsign, String country, String iata, String icao, String id, String name, String type,
			String city) {
		super();
		this.callsign = callsign;
		this.country = country;
		this.iata = iata;
		this.icao = icao;
		this.id = id;
		this.name = name;
		this.type = type;
		this.city = city;
	}
	
	/**
	 * Gets the callsign.
	 *
	 * @return the callsign
	 */
	public String getCallsign() {
		return callsign;
	}
	
	/**
	 * Sets the callsign.
	 *
	 * @param callsign the new callsign
	 */
	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}
	
	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Gets the iata.
	 *
	 * @return the iata
	 */
	public String getIata() {
		return iata;
	}
	
	/**
	 * Sets the iata.
	 *
	 * @param iata the new iata
	 */
	public void setIata(String iata) {
		this.iata = iata;
	}
	
	/**
	 * Gets the icao.
	 *
	 * @return the icao
	 */
	public String getIcao() {
		return icao;
	}
	
	/**
	 * Sets the icao.
	 *
	 * @param icao the new icao
	 */
	public void setIcao(String icao) {
		this.icao = icao;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((callsign == null) ? 0 : callsign.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((iata == null) ? 0 : iata.hashCode());
		result = prime * result + ((icao == null) ? 0 : icao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TravelBean other = (TravelBean) obj;
		if (callsign == null) {
			if (other.callsign != null)
				return false;
		} else if (!callsign.equals(other.callsign))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (iata == null) {
			if (other.iata != null)
				return false;
		} else if (!iata.equals(other.iata))
			return false;
		if (icao == null) {
			if (other.icao != null)
				return false;
		} else if (!icao.equals(other.icao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
