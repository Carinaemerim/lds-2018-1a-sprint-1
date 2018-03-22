package br.edu.ifrs.canoas.tads.tcc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * Created by rodrigo on 2/21/17.
 */
@Entity
@Data
@Inheritance
@DiscriminatorColumn(name="user_type")
public abstract class User {

	@Id @GeneratedValue
	private Long id;
	private String username;
	private boolean active;
	private String password;
    private String name;
    private String email;


	@ManyToMany(fetch = FetchType.EAGER) private Set<Role> roles;
    @OneToOne @JsonIgnore private File picture;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Message> messages;


}