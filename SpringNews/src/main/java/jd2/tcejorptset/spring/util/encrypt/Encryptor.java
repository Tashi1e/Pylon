package jd2.tcejorptset.spring.util.encrypt;

public interface Encryptor {
	
	String encrypt (String to_encrypt);
	boolean similarity (String regular, String encrypted);
	

}
