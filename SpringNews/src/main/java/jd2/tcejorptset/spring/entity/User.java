package jd2.tcejorptset.spring.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table (name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="login")
	private String login;
	
	@Column(name="password")
	private String password;
	
//	@OneToMany(fetch=FetchType.LAZY,
//			   mappedBy="users",
//			   cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//	private List<News> newsList;
//	
//	@OneToOne(fetch=FetchType.LAZY,
//			  mappedBy="users", 
//			  cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	private UserInfo userInfo;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, 
			CascadeType.DETACH,CascadeType.REFRESH })
	@JoinTable(name = "users_has_roles", 
			joinColumns = @JoinColumn(name = "users_id", referencedColumnName="id"), 
			inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName="id"))
	private List <UserRole> userRoleList;

}
