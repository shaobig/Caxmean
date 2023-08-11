package shaobig.caxmean.database.dao.abstractions;

public interface EntityDao<E, I> extends CreateDao<E, I>, ReadDao<E, I>, UpdateDao<E>, DeleteDao<I> {}
