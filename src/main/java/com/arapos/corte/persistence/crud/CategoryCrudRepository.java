package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryCrudRepository extends CrudRepository<Category,Integer> {
}
