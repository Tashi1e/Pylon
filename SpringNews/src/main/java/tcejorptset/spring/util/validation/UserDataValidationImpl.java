package tcejorptset.spring.util.validation;

public class UserDataValidationImpl implements UserDataValidation{

	@Override
	public boolean checkAUthData(String login, String password) {
		
		return (login != null && password != null);
		
	}
}
