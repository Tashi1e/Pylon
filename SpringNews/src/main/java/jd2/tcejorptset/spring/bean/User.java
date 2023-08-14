package jd2.tcejorptset.spring.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

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
//@Entity
//@Table (name="users")
public class User implements Serializable {
	
	private static final long serialVersionUID = -509093266405319422L;
	
	private int id;
	private String login;
	private String password;
	private String role;

}
