package ru.practicum.item;

import java.util.function.Function;

public class ItemMapper implements Function<ItemDto, Item> {

	@Override
	public Item apply(ItemDto itemDto) {
		if (itemDto == null) {
			return null;
		}
		return new Item(
				null,
				itemDto.getUserId(),
				itemDto.getUrl(),
				itemDto.getTags()
		);
	}
}
