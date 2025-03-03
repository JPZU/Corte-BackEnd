package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Reference;
import org.springframework.data.repository.CrudRepository;

public interface ReferenceCrudRepository extends CrudRepository<Reference, String> {
}
