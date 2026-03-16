package ru.practicum.user;

import java.time.Instant;
import java.util.function.Function;

public class UserMapper implements Function<UserDto, User> {
	@Override
	public User apply(UserDto userDTO) {
		if (userDTO == null) {
			return null;
		}
		return new User(
				userDTO.getId(),
				userDTO.getFullName().split(" ")[0],
				userDTO.getFullName().split(" ")[1],
				userDTO.getEmail(),
				Instant.from(userDTO.getRegistrationDate()),
				userDTO.getState()
		);
	}
}
