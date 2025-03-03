package com.arapos.corte.persistence.crud;

import com.arapos.corte.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Integer> {
}
