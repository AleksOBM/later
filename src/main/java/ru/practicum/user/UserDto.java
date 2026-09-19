package ru.practicum.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
	Long id;
	String email;
	String firstName;
	String lastName;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	String registrationDate;

	UserState state;
}
