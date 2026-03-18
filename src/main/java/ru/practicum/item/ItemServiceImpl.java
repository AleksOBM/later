package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.user.User;
import ru.practicum.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepository;
	private final UserService userService;

	@Override
	public List<Item> getItems(long userId) {
		User user = userService.getUser(userId).orElseThrow();
		switch (user.getState()) {
			case DELETED -> throw new RuntimeException("User is deleted");
			case BLOCKED -> throw new RuntimeException("User is blocked");
		}
		return itemRepository.findAll().stream().toList();
	}

	@Override
	public Item addNewItem(Long userId, Item item) {
		User user = userService.getUser(userId).orElseThrow();
		switch (user.getState()) {
			case DELETED -> throw new RuntimeException("User is deleted");
			case BLOCKED -> throw new RuntimeException("User is blocked");
		}
		return itemRepository.save(item);
	}

	@Override
	public void deleteItem(long userId, long itemId) {
		User user = userService.getUser(userId).orElseThrow();
		switch (user.getState()) {
			case DELETED -> throw new RuntimeException("User is deleted");
			case BLOCKED -> throw new RuntimeException("User is blocked");
		}
		itemRepository.deleteById(itemId);
	}
}
