package backendapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService<T, R> {
    List<R> findAll();

    Page<R> findAll(Pageable pageable);

    R findById(Integer id);

    R save(T t);

    R update(Integer id, T t);

    void deleteById(Integer id);

}
