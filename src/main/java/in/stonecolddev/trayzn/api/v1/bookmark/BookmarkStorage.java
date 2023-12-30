package in.stonecolddev.trayzn.api.v1.bookmark;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookmarkStorage implements Storage<Bookmark, UUID> {

  private final Logger log = LoggerFactory.getLogger(BookmarkStorage.class);

  private final BookmarkRepository bookmarkRepository;

  private final Clock clock;

  public BookmarkStorage(
      BookmarkRepository bookmarkRepository,
      Clock clock) {
    this.bookmarkRepository = bookmarkRepository;
    this.clock = clock;
  }

  public Bookmark write(Bookmark bookmark) throws Exception {
    Bookmark bookmarkWithDefaults = bookmarkWithDefaults(bookmark);
    try {
      log.info("Saving bookmark {}", bookmark);
      return bookmarkRepository.save(bookmarkWithDefaults);
    } catch (DbActionExecutionException e) {
      log.debug("***** EXCEPTION MESSAGE {}", e.getCause().getMessage());
      if (e.getCause().getMessage().toLowerCase().contains("duplicate key value")) {
        log.info("Bookmark is a duplicate, touching updated column");
        OffsetDateTime updated = bookmarkRepository.touch(bookmarkWithDefaults.uuid());
        log.debug("****** UPDATED {}", updated);
        return bookmarkWithDefaults.withUpdated(updated);
      } else {
        throw new Exception(e);
      }
    }
  }

  private Bookmark bookmarkWithDefaults(Bookmark bookmark) {
    return bookmark.withUuid(
        Optional.ofNullable(bookmark.uuid())
            .or(() -> bookmarkRepository.findByUrl(bookmark.url()).map(Bookmark::uuid))
            .orElseGet(UUID::randomUUID))
        .withCreated(OffsetDateTime.now(clock));
  }

  public Bookmark retrieve(UUID id) {
    return null;
  }

  public List<Bookmark> retrieveAll() {
    return bookmarkRepository.retrieveAll();
  }

}