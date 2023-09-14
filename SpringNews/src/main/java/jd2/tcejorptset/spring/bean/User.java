package jd2.tcejorptset.spring.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "login") 
	private String login;

	@Column(name = "password")
	private String password;

	@OneToOne(mappedBy ="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserInfo userInfo;
	
	@OneToOne(mappedBy ="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserToken userToken;

	@OneToOne(mappedBy ="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserRole userRole;
 
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public void setUserInfo (UserInfo userInfo) {
		this.userInfo = userInfo;
		this.userInfo.setUser(this);
	}
	
	public void setUserToken (UserToken userToken) { 
		this.userToken = userToken;
		this.userToken.setUser(this);
	}
	
	public void setUserRole (UserRole userRole) {
		this.userRole = userRole;
		this.userRole.setUser(this);
	}
	
}