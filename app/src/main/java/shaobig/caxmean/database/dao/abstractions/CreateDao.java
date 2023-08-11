package shaobig.caxmean.database.dao.abstractions;

public interface CreateDao<E, I> {
    I create(E entity);
}
