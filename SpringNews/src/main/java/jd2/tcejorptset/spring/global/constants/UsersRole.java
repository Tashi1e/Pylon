package jd2.tcejorptset.spring.global.constants;

public enum UsersRole {
	
	ADMIN,
	USER,
	GUEST;
	
	public String getRole () {
		return name().toLowerCase();
	}

}
