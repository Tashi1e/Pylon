package jd2.tcejorptset.spring.bean;

public enum UserRole {
	
	ADMIN,
	EDITOR,
	USER,
	GUEST;
	
	public String getRole () {
		return name().toLowerCase();
	}

}
