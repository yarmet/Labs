package com.company.lab1.services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.company.database.models.Sensor;
import java.util.List;

/**
 * Created by khamedov on 19.07.2017.
 */
@Repository
public interface SensorService extends CrudRepository<Sensor, Long> {

    @Query("select c from Sensor c where c.name = :name")
    List<Sensor> findByName(@Param("name") String name);



}