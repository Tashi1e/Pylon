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
public class UserData {
	
	private User user;
	private UserInfo userInfo;
	private String rememberMeCheckBox;
}
