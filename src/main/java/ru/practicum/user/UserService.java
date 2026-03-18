package ru.practicum.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

	Optional<User> getUser(long userId);

	List<User> getAllUsers();

	User saveUser(User user);
}
