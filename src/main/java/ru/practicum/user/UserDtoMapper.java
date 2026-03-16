package ru.practicum.user;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Function;

public class UserDtoMapper implements Function<User, UserDto> {

	@Override
	public UserDto apply(User user) {
		if (user == null) {
			return null;
		}
		return new UserDto(
				user.getId(),
				String.format("%s %s", user.getFirstName(), user.getLastName()),
				user.getEmail(),
				LocalDateTime.ofInstant(user.getRegistrationDate(), ZoneId.systemDefault()),
				user.getState()
				);
	}
}
