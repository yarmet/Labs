package com.company.lab1.services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.company.database.models.Parameter;

import java.util.List;

/**
 * Created by khamedov on 19.07.2017.
 */
@Repository
public interface ParameterService extends CrudRepository<Parameter, Long> {

    @Query("select c from Parameter c where c.name = :name")
    List<Parameter> findByName(@Param("name") String name);

}