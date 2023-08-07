package tcejorptset.spring.controller.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import tcejorptset.spring.dao.impl.pool.ConnectionPool;
import tcejorptset.spring.dao.impl.pool.ConnectionPoolException;

public class ConnectionPoolOpsListener implements ServletContextListener{
	
	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			connectionPool.initPoolData();
			System.out.println("Connection Pool Initialize Successful!"); //TEST
		}
		catch(ConnectionPoolException e){
			// TODO logination
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		connectionPool.dispose();
	}

}
