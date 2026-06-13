package ru.practicum.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String registrationDate;

    private UserState state;
}
