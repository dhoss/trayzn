package in.stonecolddev.trayzn.api.v1.bookmark;

import java.util.List;
import java.util.Optional;

public interface Storage<T, U> {

  T write(T item) throws Exception;

  Optional<T> retrieve(U id);


  // TODO: Storage retrieveAll pagination
  List<T> retrieveAll();

}