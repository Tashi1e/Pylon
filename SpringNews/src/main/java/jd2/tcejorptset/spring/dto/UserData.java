package jd2.tcejorptset.spring.dto;

import org.springframework.stereotype.Component;

import jd2.tcejorptset.spring.entity.User;
import jd2.tcejorptset.spring.entity.UserInfo;
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
}
