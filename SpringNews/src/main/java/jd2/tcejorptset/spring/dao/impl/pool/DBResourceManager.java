package jd2.tcejorptset.spring.dao.impl.pool;

import java.util.ResourceBundle;

@Deprecated
public class DBResourceManager {

	private final static DBResourceManager instance = new DBResourceManager();
	private ResourceBundle bundle = ResourceBundle.getBundle("connectionpool.db");
	
	public static DBResourceManager getInstance() {
	return instance;
	}

	public String getValue(String key){
	return bundle.getString(key);
	}
	
}
