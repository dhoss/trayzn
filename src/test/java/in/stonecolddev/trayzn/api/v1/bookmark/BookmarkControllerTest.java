package in.stonecolddev.trayzn.api.v1.bookmark;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("it-test")
@Tag("it-test")
class BookmarkControllerTest {

  @Autowired
  private MockMvc api;

  @Test
  public void addBookmarkAuthorized() throws Exception {
    api.perform(
      post("/api/v1/bookmarks/add")
        .content("""
                 {
                   "url": "http://google.com",
                   "title": "test",
                   "favIconUrl": "http://google.com/favicon.ico"
                 }
                 """)
        .contentType(MediaType.APPLICATION_JSON)
        .header("X-API-Key", "test")
        .header("X-API-Secret", "test"))
      .andExpect(status().isOk());
  }

  @Test
  public void addBookmarkUnauthorized() {
    assertThrows(
      ServletException.class,
      () -> api.perform(
        post("/api/v1/bookmarks/add")
          .content("""
               {
                 "url": "http://google.com",
                 "title": "test",
                 "favIconUrl": "http://google.com/favicon.ico"
               }
               """)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isUnauthorized()));
  }
}