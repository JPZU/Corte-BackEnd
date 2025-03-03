package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Cloth;
import org.springframework.data.repository.CrudRepository;

public interface ClothCrudRepository extends CrudRepository<Cloth, Integer> {
}
