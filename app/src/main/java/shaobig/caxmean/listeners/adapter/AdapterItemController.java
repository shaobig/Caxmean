package shaobig.caxmean.listeners.adapter;

public interface AdapterItemController<E> {
    void addItem(E entity);
    void updateItem(E entity);
    void deleteItem(E entity);
}
