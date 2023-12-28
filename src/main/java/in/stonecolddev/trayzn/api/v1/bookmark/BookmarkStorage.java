package in.stonecolddev.trayzn.api.v1.bookmark;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class BookmarkStorage implements Storage<Bookmark, UUID> {

  private final BookmarkRepository bookmarkRepository;

  private final Clock clock;

  public BookmarkStorage(
      BookmarkRepository bookmarkRepository,
      Clock clock) {
    this.bookmarkRepository = bookmarkRepository;
    this.clock = clock;
  }

  public Bookmark write(Bookmark bookmark) {
    return bookmarkRepository.save(
        bookmark.withUuid(UUID.randomUUID())
            .withCreated(OffsetDateTime.now(clock)));
  }

  public Bookmark retrieve(UUID id) {
    return null;
  }

  public List<Bookmark> retrieveAll() {
    return bookmarkRepository.retrieveAll();
  }

}