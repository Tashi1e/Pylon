package jd2.tcejorptset.spring.util.encrypt;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component("BCrypt")
public class HashB implements Encryptor {

	@Override
	public String encrypt(String toEncrypt) {
		return BCrypt.hashpw(toEncrypt, BCrypt.gensalt());
	}
	
	@Override
	public boolean similarity (String regular, String encrypted) {
		return BCrypt.checkpw(regular, encrypted);
	}

}
