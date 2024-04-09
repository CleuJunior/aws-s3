package backendapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService<D> {
    List<D> findAll();

    Page<D> findAll(Pageable pageable);

    D findById(Integer id);

    D save(D d);

    D update(Integer id, D d);

    void deleteById(Integer id);

}
