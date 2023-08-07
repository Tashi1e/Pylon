package jd2.tcejorptset.spring.service.impl;

import jd2.tcejorptset.spring.dao.impl.pool.ConnectionPool;
import jd2.tcejorptset.spring.dao.impl.pool.ConnectionPoolException;
import jd2.tcejorptset.spring.service.ConnectionService;
import jd2.tcejorptset.spring.service.ServiceException;

@Deprecated
public class ConnectionServiceImpl implements ConnectionService {
	
	ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void initPool() throws ServiceException {
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void closePool() {
			connectionPool.dispose();
	}

}
