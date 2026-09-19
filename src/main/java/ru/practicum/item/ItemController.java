package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.item.dto.AddItemRequest;
import ru.practicum.item.dto.GetItemRequest;
import ru.practicum.item.dto.ItemDto;
import ru.practicum.item.dto.ModifyItemRequest;
import ru.practicum.item.model.ItemCountByUser;
import ru.practicum.item.model.ItemInfo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;

	@GetMapping(headers = "X-Later-User-Id")
	public List<ItemDto> get(
			@RequestHeader("X-Later-User-Id") long userId,
			@RequestParam(name = "state", defaultValue = "unread") String state,
			@RequestParam(name = "contentType", defaultValue = "all") String contentType,
			@RequestParam(name = "sort", defaultValue = "newest") String sort,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "tags", required = false) List<String> tags
	) {
		return itemService.getItems(GetItemRequest.of(userId, state, contentType, sort, limit, tags));
	}

	@GetMapping(params = "lastName")
	public List<ItemDto> get(@RequestParam(name = "lastName") String lastName) {
		return itemService.getUserItems(lastName);
	}

	@GetMapping("/count/date")
	public List<ItemCountByUser> getCountsByDates(@RequestParam LocalDate from, @RequestParam LocalDate to) {
		return itemService.getCountsByDates(from, to);
	}

	@GetMapping("/users/{userId}")
	public List<ItemInfo> getAllByUserId(@PathVariable Long userId) {
		return itemService.getAllByUserId(userId);
	}

	@GetMapping("/count")
	public List<ItemCountByUser> getCountsByUrl(@RequestParam String url) {
		return itemService.getCountsByUrl(url);
	}

	@PostMapping(headers = "X-Later-User-Id")
	public ItemDto add(@RequestHeader("X-Later-User-Id") Long userId,
	                   @RequestBody AddItemRequest request) {
		return itemService.addNewItem(userId, request);
	}

	@DeleteMapping(path = "/{itemId}", headers = "X-Later-User-Id")
	public void deleteItem(@RequestHeader("X-Later-User-Id") long userId,
	                       @PathVariable(name = "itemId") long itemId) {
		itemService.deleteItem(userId, itemId);
	}

	@PatchMapping(headers = "X-Later-User-Id")
	public ItemDto modifyItem(@RequestHeader("X-Later-User-Id") long userId,
	                          @RequestBody ModifyItemRequest request) {
		return itemService.changeItem(userId, request);
	}
}