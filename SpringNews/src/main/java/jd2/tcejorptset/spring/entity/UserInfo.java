package jd2.tcejorptset.spring.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
public class UserInfo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
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
	
	@OneToOne(mappedBy="userInfo", cascade=CascadeType.ALL)
	private User user;
	
}
