package ru.practicum.user;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.function.Function;

public class UserMapper implements Function<UserDto, User> {
	@Override
	public User apply(UserDto userDTO) {
		if (userDTO == null) {
			return null;
		}
		return new User(
				null,
				userDTO.getFullName().split(" ")[0],
				userDTO.getFullName().split(" ")[1],
				userDTO.getEmail(),
				userDTO.getRegistrationDate() == null ? Instant.now() :
						userDTO.getRegistrationDate().toInstant(ZoneOffset.from(ZoneOffset.UTC)),
				userDTO.getState()
		);
	}
}
