package ru.practicum.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
	private Long id;
	private String fullName;
	private String email;

	@DateTimeFormat(pattern = "yyyy.MM.dd, hh:mm:ss")
	@JsonFormat(pattern = "yyyy.MM.dd, hh:mm:ss")
	private LocalDateTime registrationDate;

	private UserState state;
}
