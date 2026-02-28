package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.util.IdentifyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FakeItemRepository implements ItemRepository {

	private final Map<Long, Item> items =  new HashMap<>();
	private final IdentifyService identifyService;

	@Override
	public List<Item> findAll() {
		return items.values().stream().toList();
	}

	@Override
	public Item save(Long userId, Item item) {
		item.setUserId(userId);
		item.setId(identifyService.getNextId(items));
		items.put(item.getId(), item);
		return item;
	}

	@Override
	public void remove(long userId, long itemId) {
		items.remove(itemId);
	}
}
