import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import ru.practicum.LaterApplication;
import ru.practicum.user.User;
import ru.practicum.user.UserDto;
import ru.practicum.user.UserServiceImpl;
import ru.practicum.user.UserState;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Transactional
@Import(UserServiceImpl.class)
@ContextConfiguration(classes = LaterApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(properties = "spring.datasource.url=jdbc:postgresql://localhost:5432/test")
class UserServiceImplTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserServiceImpl userService;

	@Test
	void testSaveUser() {
		UserDto userDto = makeUserDto("some@email.com", "Пётр", "Иванов");

		userService.saveUser(userDto);

		TypedQuery<User> query = em.createQuery("Select u from User u where u.email = :email", User.class);
		User user = query.setParameter("email", userDto.getEmail()).getSingleResult();

		assertThat(user, matchesUser(userDto));
	}

	@Test
	void testGetAllUsers() {
		List<UserDto> userDtos = List.of(
		makeUserDto("some1@email.com", "Пётр1", "Иванов1"),
		makeUserDto("some2@email.com", "Пётр2", "Иванов2"),
		makeUserDto("some3@email.com", "Пётр3", "Иванов3")
		);

		userDtos.forEach(userService::saveUser);

		List<User> users = em.createQuery("select u from User u", User.class)
				.getResultList();

		assertThat(users, hasSize(3));

		assertThat(users, contains(
				matchesUser(userDtos.get(0)),
				matchesUser(userDtos.get(1)),
				matchesUser(userDtos.get(2))
		));
	}

	private Matcher<User> matchesUser(UserDto dto) {
		return allOf(
				hasProperty("id", notNullValue()),
				hasProperty("firstName", equalTo(dto.getFirstName())),
				hasProperty("lastName", equalTo(dto.getLastName())),
				hasProperty("email", equalTo(dto.getEmail())),
				hasProperty("state", equalTo(dto.getState())),
				hasProperty("registrationDate", notNullValue())
		);
	}

	private UserDto makeUserDto(String email, String firstName, String lastName) {
		UserDto dto = new UserDto();
		dto.setEmail(email);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setState(UserState.ACTIVE);

		return dto;
	}
}
