package in.stonecolddev.trayzn.api.v1.bookmark;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.net.MalformedURLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("it-test")
@Tag("it-test")
public class BookmarkRepositoryIntegrationTest {

  @Autowired
  private BookmarkRepository bookmarkRepository;

  @Test
  public void save() {
    var bookmark = BookmarkBuilder.builder()
        .uuid(UUID.randomUUID())
        .url("http://www.google.com/")
        .title("Google")
        .favIconUrl("http://google.com/favicon.ico")
        .build();

    var saved = bookmarkRepository.save(bookmark);

    assertEquals(saved.url(), bookmark.url());
    assertEquals(saved.title(), bookmark.title());
    assertEquals(saved.favIconUrl(), bookmark.favIconUrl());
  }

}