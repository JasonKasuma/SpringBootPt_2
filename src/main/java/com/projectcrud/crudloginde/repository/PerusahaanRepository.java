package com.projectcrud.crudloginde.repository;

import com.projectcrud.crudloginde.model.Perusahaan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerusahaanRepository extends CrudRepository<Perusahaan, Long> {
}
