package in.stonecolddev.trayzn.api.v1.bookmark;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.test.context.ActiveProfiles;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("unit-test")
@Tag("unit-test")
class BookmarkStorageTest {

  BookmarkRepository repository = mock(BookmarkRepository.class);

  DbActionExecutionException dbe = mock(DbActionExecutionException.class);

  @Test
  public void write() throws Exception {
    Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    BookmarkStorage storage = new BookmarkStorage(repository, clock);
    Bookmark bm = BookmarkBuilder.builder()
        .url("http://google.com")
        .favIconUrl("http://google.com/favicon.ico")
        .title("Google")
        .build();
    UUID uuid = UUID.randomUUID();
    Bookmark bmWithDefaults =
        bm.withCreated(OffsetDateTime.now(clock)).withUuid(uuid);

    // new bookmark
    when(repository.findByUrl(bm.url())).thenReturn(Optional.empty());
    when(repository.save(any())).thenReturn(bmWithDefaults);

    assertEquals(bmWithDefaults, storage.write(bm));
    verify(repository, times(1)).findByUrl(bm.url());
    verify(repository, times(0)).touch(uuid);

    reset(repository);

    // bookmark exists
    when(repository.findByUrl(bm.url())).thenReturn(Optional.of(bmWithDefaults));
    when(dbe.getCause()).thenReturn(new Throwable("ERROR: duplicate key value violates unique constraint"));
    when(repository.save(any())).thenThrow(dbe);

    OffsetDateTime updatedTouched = OffsetDateTime.now(clock).plusMinutes(1L);
    when(repository.touch(uuid)).thenReturn(updatedTouched);

    assertEquals(bmWithDefaults.withUpdated(updatedTouched), storage.write(bm));
    verify(repository, times(1)).touch(uuid);

    reset(repository);

    // unaccounted for database exception
    when(repository.findByUrl(bm.url())).thenReturn(Optional.of(bmWithDefaults));
    when(dbe.getCause()).thenReturn(new Throwable("some other error"));
    when(repository.save(any())).thenThrow(dbe);

    assertThrows(Exception.class, () -> storage.write(bm));
  }
}