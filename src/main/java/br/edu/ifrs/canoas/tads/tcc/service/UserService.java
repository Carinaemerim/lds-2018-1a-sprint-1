package br.edu.ifrs.canoas.tads.tcc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.domain.User;
import br.edu.ifrs.canoas.tads.tcc.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User save(User user) {

		User fetchedUser = this.getOne(user);

		if (fetchedUser == null || fetchedUser.getId() == null)
			return null;

		fetchedUser.setName(user.getName());
		fetchedUser.setEmail(user.getEmail());
		fetchedUser.setSkill(user.getSkill());
		fetchedUser.setExperience(user.getExperience());

		return userRepository.save(fetchedUser);
	}

	public User getOne(User user) {

		if (user == null || user.getId() == null)
			return null;

		Optional<User> optionalUser = userRepository.findById(user.getId());

		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

	/**
	 * modificar depois para trazer apenas professores/orientadores
	 *
	 */
	public Iterable<User> getAdvisors() {
		return userRepository.findAll();
	}

}
