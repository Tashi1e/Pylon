package jd2.tcejorptset.spring.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
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
	
	private static final long serialVersionUID = 9083880725683070107L;

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
	
	@OneToMany (fetch = FetchType.LAZY)
	@JoinColumn (name = "authors_email")
	private List <News> newsList;
}

