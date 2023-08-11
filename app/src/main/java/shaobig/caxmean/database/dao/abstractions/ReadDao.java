package shaobig.caxmean.database.dao.abstractions;

import java.util.List;

public interface ReadDao<E, I> {
    E readById(I id);
    List<E> readAll();
}
