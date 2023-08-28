package jd2.tcejorptset.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
@Table(name = "user_token")
public class UserToken {

	@Id
	@Column(name = "users_id")
	private int id;
	
	@Column(name = "selector")
	private String selector;

	@Column(name = "validator")
	private String validator;

	@OneToOne (fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "users_login")
	private User user;

}
