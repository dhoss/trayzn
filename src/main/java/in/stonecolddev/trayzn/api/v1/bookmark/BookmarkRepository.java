package in.stonecolddev.trayzn.api.v1.bookmark;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

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
        created desc
      """)
  List<Bookmark> retrieveAll();
}