package org.domainname.entity;

public enum AptType {
	APARTMENT("apartment"),
	HOUSE("house");
	
	private final String aptType;
	
	private AptType(String aptType) {
		this.aptType = aptType;
	}
	
	public String getAptType() {
		return this.aptType;
	}
}
