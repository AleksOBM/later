package ru.practicum.user;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
class UserMapper {

	public UserDto mapToUserDto(@NonNull User user) {
		String regDate = DateTimeFormatter
				.ofPattern("yyyy.MM.dd hh:mm:ss")
				.withZone(ZoneOffset.UTC)
				.format(user.getRegistrationDate());

		return new UserDto(
				user.getId(),
				user.getEmail(),
				user.getFirstName(),
				user.getLastName(),
				regDate,
				user.getState()
		);
	}

	public List<UserDto> mapToUserDto(@NonNull Iterable<User> users) {
		var result = new ArrayList<UserDto>();
		users.forEach(user -> result.add(mapToUserDto(user)));
		return result;
	}

	public User mapToNewUser(@NonNull UserDto userDto) {
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setState(userDto.getState());
		return user;
	}
}
