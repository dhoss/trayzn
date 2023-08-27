package in.stonecolddev.trayzn.api.v1.bookmark;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.Optional;

@Table("bookmarks")
@RecordBuilder
public record Bookmark(
  @Id
  Integer id,
  URL url,
  String title,
  @Column("favicon_url")
  Optional<String> favIconUrl,
  OffsetDateTime created,
  OffsetDateTime updated,
  @Column("last_snapshot")
  OffsetDateTime lastSnapshot
) {}