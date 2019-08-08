package com.projectcrud.crudloginde.repository;

import com.projectcrud.crudloginde.model.Transaksi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiRepository extends CrudRepository <Transaksi, Long> {
        }
