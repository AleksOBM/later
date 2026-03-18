package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
	private final UserRepository repository;

	@Override
	public Optional<User> getUser(long userId) {
		return repository.findById(userId);
	}

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}
}
