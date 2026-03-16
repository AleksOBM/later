package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@GetMapping
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers().stream().map(new UserDtoMapper()).toList();
	}

	@PostMapping
	public UserDto saveNewUser(@RequestBody User user) {
		return new UserDtoMapper().apply(userService.saveUser(user));
	}
}
