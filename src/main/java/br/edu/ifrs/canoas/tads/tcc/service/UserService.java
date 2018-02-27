package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.User;
import br.edu.ifrs.canoas.tads.tcc.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User save(User user) {
		User fetchedUser = this.getOne(user);
		fetchedUser.setName(user.getName());
		fetchedUser.setEmail(user.getEmail());
		fetchedUser.setSkill(user.getSkill());
		fetchedUser.setExperience(user.getExperience());
		return userRepository.save(fetchedUser);
	}

	public User getOne(User user) {
		return userRepository.findById(user.getId()).get();
	}

}
