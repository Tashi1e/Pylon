package jd2.tcejorptset.spring.bean;

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
public class AuthorizedUserData {

	private String userRole;
	private String userNick;
	private String userEmail;
	
}
