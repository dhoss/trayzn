package in.stonecolddev.trayzn.api.v1.bookmark;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.net.URL;
import java.time.OffsetDateTime;

@RecordBuilder
public record Bookmark(
  @Id
  Integer id,
  URL url,
  String title,
  String favIconUrl,
  OffsetDateTime created,
  OffsetDateTime updated,
  OffsetDateTime lastSnapshot
) {}