package br.edu.ifrs.canoas.tads.tcc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by rodrigo on 3/18/17.
 */
@Entity
@Data
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	private String role;
	@ManyToMany
	private Set<User> accounts;
}
