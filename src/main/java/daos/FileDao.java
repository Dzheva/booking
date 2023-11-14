package daos;

import java.util.List;

public interface FileDao<T> {
    void create(T model);

    T get(int id);

    List<T> getAll();

    int getNextId();

    void saveAll();
}
