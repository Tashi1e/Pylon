package jd2.tcejorptset.spring.bean;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "news")
public class News implements Serializable {

	private static final long serialVersionUID = 5998288721757839147L;  

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;

	@Column(name = "brief")
	private String brief;

	@Column(name = "content")
	private File content;

	@Column(name = "news_date")
	private Timestamp date;

	@Column(name = "news_image")
	private File image;

	@Column(name = "status")
	private short status;
	
	@ManyToOne
	@JoinColumn(name = "authors_email", referencedColumnName = "email")
	private UserInfo userInfo;

	@Transient
	private String author;
	
	@Transient
	private int [] markedNewsId;

}
