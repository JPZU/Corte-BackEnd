package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierCrudRepository extends CrudRepository<Supplier, String> {
}
