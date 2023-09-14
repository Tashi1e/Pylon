package jd2.tcejorptset.spring.util.encrypt;

import org.springframework.stereotype.Component;

import com.lambdaworks.crypto.SCryptUtil;

@Component ("SCrypt")
public class HashS implements Encryptor {

	@Override
	public String encrypt(String toEncrypt) {
		return SCryptUtil.scrypt(toEncrypt, 16, 16, 16);
	}

	@Override
	public boolean similarity (String regular, String encrypted) {
		return SCryptUtil.check(regular, encrypted);
	}

}
