package in.stonecolddev.trayzn.api.v1.bookmark;

import java.io.InputStream;
import java.util.List;

public interface Bookmarker {

  Bookmark save(Bookmark bookmark);

  Bookmark find(String slug);

  void delete(String slug);

  List<Bookmark> list(Integer lastSeen, Integer pageSize);

  List<Bookmark> search(String query);

  List<Bookmark> ingest(InputStream bookmarkStream);

}