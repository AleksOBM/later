package ru.practicum.item;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class ItemDto {
	private Long id;
	private Long userId;
	private String url;
	private Set<String> tags = new HashSet<>();
}
