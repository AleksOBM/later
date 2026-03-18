package ru.practicum.item;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	private String url;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "item_id"))
	@Column(name = "name")
	private Set<String> tags = new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Item)) {
			return false;
		}
		return id != null && id.equals(((Item) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
