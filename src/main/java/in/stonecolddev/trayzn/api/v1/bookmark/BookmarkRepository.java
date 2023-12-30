package in.stonecolddev.trayzn.api.v1.bookmark;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookmarkRepository extends CrudRepository<Bookmark, Integer> {

  @Query("""
      select
          uuid
        , url
        , title
        , favicon_url
        , created
        , updated
        , last_snapshot
      from
        bookmarks b
      order by
        b.created desc
      """)
  List<Bookmark> retrieveAll();

  @Query("""
      select
          uuid
        , url
        , title
        , favicon_url
        , created
        , updated
        , last_snapshot
      from
        bookmarks b
      where
        uuid = :uuid
      limit 1
      """)
  Optional<Bookmark> findByUuid(@Param("uuid") UUID uuid);

  @Query(
      """
      select
          uuid
        , url
        , title
        , favicon_url
        , created
        , updated
        , last_snapshot
      from bookmarks b
      where
        url = :url
      limit 1
      """
  )
  Optional<Bookmark> findByUrl(@Param("url") String url);

  @Query("""
      update
        bookmarks b
      set
        updated = now()
      where uuid = :uuid
      returning updated
       """)
  OffsetDateTime touch(UUID uuid);

}