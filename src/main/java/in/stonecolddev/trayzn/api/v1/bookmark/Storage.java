package in.stonecolddev.trayzn.api.v1.bookmark;

import java.util.List;

public interface Storage<T, U> {

  T write(T item);

  T retrieve(U id);


  // TODO: Storage retrieveAll pagination
  List<T> retrieveAll();

}