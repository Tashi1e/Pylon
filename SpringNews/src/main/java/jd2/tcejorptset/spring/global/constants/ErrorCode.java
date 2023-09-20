package jd2.tcejorptset.spring.global.constants;

public enum ErrorCode {
	ADD_NEWS,
	UPDATE_NEWS,
	DELETE_NEWS,
	REGISTRATION_SUCCESSFUL,
	REGISTRATION_FAILED,
	LOGIN_EXISTS,
	EMAIL_EXISTS,
	WRONG_LOGIN,
	WRONG_PASSWORD,
	SIGN_IN,
	TOKEN_UPDATE,
	FETCH_NEWS,
	LATEST_NEWS_LITS;

	public String getCode () {
		return name().toLowerCase();
	}
}


