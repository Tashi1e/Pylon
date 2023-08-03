package tcejorptset.spring.bean;

import java.io.Serializable;

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
public class User implements Serializable {
	
	private static final long serialVersionUID = -509093266405319422L;
	
	private String login;
	private String password;
	private String role;

}
