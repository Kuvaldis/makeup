package kuvaldis.makeup.lib.data.dao

/**
 * @author Kuvaldis
 * Create date: 13.12.13 23:42
 */
public interface Dao<T> {

    T find(Object id);
    T create(T t);
    def update(T t);
    def delete(Object id);
}