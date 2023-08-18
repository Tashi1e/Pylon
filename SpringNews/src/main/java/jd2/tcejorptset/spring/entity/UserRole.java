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
import javax.persistence.Table;

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

@Entity
@Table(name = "roles")
public class UserRole {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "role_name")
	private String role;

//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
//			CascadeType.REFRESH })
//	@JoinTable(name = "users_has_roles", 
//	joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"), 
//	inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"))
//	private List<User> userList;
	
	public UserRole (String role) {
		this.role = role;
	}
}
