package in.stonecolddev.trayzn.api.v1.bookmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/bookmarks", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookmarkController {

  private final Logger log = LoggerFactory.getLogger(BookmarkController.class);

  @PostMapping("/add")
  public HttpEntity<Map<String,String>> create(@RequestBody Map<String, String> link) {
    log.debug("***** ADD {}", link);
    return ResponseEntity.ok(link);
  }
}