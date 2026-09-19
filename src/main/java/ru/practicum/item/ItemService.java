package ru.practicum.item;

import org.springframework.transaction.annotation.Transactional;
import ru.practicum.item.dto.AddItemRequest;
import ru.practicum.item.dto.GetItemRequest;
import ru.practicum.item.dto.ItemDto;
import ru.practicum.item.dto.ModifyItemRequest;
import ru.practicum.item.model.ItemCountByUser;
import ru.practicum.item.model.ItemInfo;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
interface ItemService {

	@Transactional
	ItemDto addNewItem(Long userId, AddItemRequest request);

	@Transactional
	void deleteItem(long userId, long itemId);

	@Transactional
	ItemDto changeItem(long userId, ModifyItemRequest request);

	List<ItemDto> getItems(GetItemRequest req);

	List<ItemDto> getUserItems(String lastName);

	List<ItemCountByUser> getCountsByDates(LocalDate from, LocalDate to);

	List<ItemCountByUser> getCountsByUrl(String url);

	List<ItemInfo> getAllByUserId(Long userId);
}
