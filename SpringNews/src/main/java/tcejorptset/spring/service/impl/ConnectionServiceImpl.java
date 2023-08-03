package tcejorptset.spring.service.impl;

import tcejorptset.spring.dao.impl.pool.ConnectionPool;
import tcejorptset.spring.dao.impl.pool.ConnectionPoolException;
import tcejorptset.spring.service.ConnectionService;
import tcejorptset.spring.service.ServiceException;

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
