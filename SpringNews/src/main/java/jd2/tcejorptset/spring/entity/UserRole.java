package jd2.tcejorptset.spring.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Table(name = "authority")
public class UserRole {

	@Id
	@Column(name = "users_login")
	private String login;

	@Column(name = "authority")
	private String role;

	@ManyToOne (fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "users_login")
	private User rolesUser;
		
	public UserRole (String role) {
		this.role = role;
	}
}

