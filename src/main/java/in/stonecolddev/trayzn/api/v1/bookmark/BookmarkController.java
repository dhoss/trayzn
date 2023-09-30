package in.stonecolddev.trayzn.api.v1.bookmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/bookmarks", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookmarkController {

  private final Logger log = LoggerFactory.getLogger(BookmarkController.class);

  private final BookmarkStorage bookmarkStorage;

  public BookmarkController(BookmarkStorage bookmarkStorage) {
    this.bookmarkStorage = bookmarkStorage;
  }

  @PostMapping("/add")
  public HttpEntity<Bookmark> create(@RequestBody Bookmark bookmark) throws Exception {
    log.info("Adding bookmark {}", bookmark);
    return ResponseEntity.ok(bookmarkStorage.write(bookmark));
  }

  // TODO: bookmark listing pagination
  @GetMapping("")
  public HttpEntity<List<Bookmark>> list() {
    log.info("Listing bookmarks");
    return ResponseEntity.ok(bookmarkStorage.retrieveAll());
  }
}