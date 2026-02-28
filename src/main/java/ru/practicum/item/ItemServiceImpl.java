package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;

	@Override
	public List<Item> getItems(long userId) {
		return itemRepository.findAll().stream().filter(item -> item.getUserId() == userId).toList();
	}

	@Override
	public Item addNewItem(Long userId, Item item) {
		return itemRepository.save(userId, item);
	}

	@Override
	public void deleteItem(long userId, long itemId) {
		itemRepository.remove(userId, itemId);
	}
}
