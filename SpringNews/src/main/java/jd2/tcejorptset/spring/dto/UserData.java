package jd2.tcejorptset.spring.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

@Component
public class UserData {
	
	private int id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String nickName;
	private String email;
	private Timestamp userRegDate;
}
