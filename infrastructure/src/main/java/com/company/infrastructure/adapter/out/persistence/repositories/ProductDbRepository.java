package com.company.infrastructure.adapter.out.persistence.repositories;

import com.company.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDbRepository extends CrudRepository<ProductEntity, Long> {
    boolean existsByName(String name);
}
