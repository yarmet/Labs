package com.company.database.models;

import lombok.Data;
import javax.persistence.*;

/**
 * Created by khamedov on 19.07.2017.
 */
@Data
@Entity
@Table(name = "coordinate")
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "latitude")
    private long latitude;

    @Column(name = "longitude")
    private long longitude;

}
