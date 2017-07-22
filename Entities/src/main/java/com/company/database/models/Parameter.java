package com.company.database.models;

import lombok.Data;
import javax.persistence.*;

/**
 * Created by khamedov on 19.07.2017.
 */
@Data
@Entity
@Table(name = "parameter")
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "parameterId")
    private String parameterId;

    @Column(name = "unit")
    private String unit;

}
