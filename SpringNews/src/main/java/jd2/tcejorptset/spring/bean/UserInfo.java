package jd2.tcejorptset.spring.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter 
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

@Component
@Entity
@Table(name="user_details")
public class UserInfo implements Serializable{
	
	private static final long serialVersionUID = -7073204465384835038L;

	@Id
	@Column(name = "users_login")
	private  String login; 
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="nickname")
	private String nickName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="register_date")
	private Timestamp userRegDate;
	
	@OneToOne (fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "users_login")
	private User user;
	
//	@Id
////	@OneToOne(mappedBy="userInfo", cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	@OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
////	@PrimaryKeyJoinColumn
//	@JoinColumn(name = "users_id")
//	@MapsId
//	private User user;
}

