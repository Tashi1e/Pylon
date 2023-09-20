package jd2.tcejorptset.spring.util.encrypt;

public interface Encryptor {
	
	String encrypt (String toEncrypt);
	boolean similarity (String regular, String encrypted);
	

}
