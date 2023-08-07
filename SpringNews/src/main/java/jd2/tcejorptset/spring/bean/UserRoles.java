package jd2.tcejorptset.spring.bean;

public enum UserRoles {
	
	ADMIN,
	EDITOR,
	USER,
	GUEST;
	
	public String getRole () {
		return name().toLowerCase();
	}

}
