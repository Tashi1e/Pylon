package tcejorptset.spring.service;

@Deprecated
public interface ConnectionService {
	
	void initPool() throws ServiceException;
	void closePool();

}