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
public class User {

	@Id @GeneratedValue private Long id;
	private String username;
	private boolean active;
	private String password;
    private String name;
    private String email;

	private String lattes;
    private String experience;
    private String skill;

	@ManyToMany(fetch = FetchType.EAGER) private Set<Role> roles;
    @OneToOne @JsonIgnore private File picture;

    @ManyToMany
    private List<EvaluationBoard> board;
	
	

}