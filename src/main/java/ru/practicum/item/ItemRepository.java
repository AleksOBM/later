package ru.practicum.item;

import java.util.List;

public interface ItemRepository {

	List<Item> findAll();

	Item save(Long userId, Item item);

	void remove(long userId, long itemId);
}
