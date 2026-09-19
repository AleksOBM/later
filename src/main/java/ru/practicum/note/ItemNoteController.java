package ru.practicum.note;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class ItemNoteController {

	private final ItemNoteService itemNoteService;

	@GetMapping(params = "url", headers = "X-Later-User-Id")
	public List<ItemNoteDto> searchByUrl(@RequestHeader("X-Later-User-Id") long userId,
	                                     @RequestParam(name = "url") String url) {
		return itemNoteService.searchNotesByUrl(url, userId);
	}

	@GetMapping(params = "tag", headers = "X-Later-User-Id")
	public List<ItemNoteDto> searchByTags(@RequestHeader("X-Later-User-Id") long userId,
	                                      @RequestParam(name = "tag") String tag) {
		return itemNoteService.searchNotesByTag(userId, tag);
	}

	@GetMapping(headers = "X-Later-User-Id")
	public List<ItemNoteDto> listAllNotes(@RequestHeader("X-Later-User-Id") long userId,
	                                      @RequestParam(name = "from", defaultValue = "0") int from,
	                                      @RequestParam(name = "size", defaultValue = "10") int size) {
		return itemNoteService.listAllItemsWithNotes(userId, from, size);
	}

	@PostMapping(headers = "X-Later-User-Id")
	public ItemNoteDto add(@RequestHeader("X-Later-User-Id") Long userId, @RequestBody ItemNoteDto itemNote) {
		return itemNoteService.addNewItemNote(userId, itemNote);
	}
}