package br.edu.ifrs.canoas.tads.tcc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Created by rodrigo on 2/21/17.
 */
@Entity
@Data
public class User {

	@Id @GeneratedValue private Long id;
	private String username;
	private boolean active;
	private String password;
	private String lattes;
	@ManyToMany(fetch = FetchType.EAGER) private Set<Role> roles;
	@NotBlank private String name;
    @NotBlank @javax.validation.constraints.Email private String email;
	@NotBlank private String experience;
	@NotBlank private String skill;
    @OneToOne @JsonIgnore private File picture;
	
	

}