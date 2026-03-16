package ru.practicum.item;

import java.util.function.Function;

public class ItemDtoMapper implements Function<Item, ItemDto> {

	@Override
	public ItemDto apply(Item item) {
		if (item == null) {
			return null;
		}
		return new ItemDto(
				item.getId(),
				item.getUserId(),
				item.getUrl(),
				item.getTags()
		);
	}
}
