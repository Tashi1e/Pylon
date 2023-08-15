package jd2.tcejorptset.spring.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

@Entity
@Table(name="user_details")
public class UserInfo{

	@Id
	@Column(name = "users_id")
	private int id;
	
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "users_id")
	private User user;
	
}
