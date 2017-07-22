package com.company.webservice.entities;

import com.company.webservice.EndpointUrl;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khamedov on 12.07.2017.
 */

@Getter
@Setter
@XmlRootElement(name = "itemResponse", namespace = EndpointUrl.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"code", "items"})
public class ItemResponse {

    @XmlElement
    private int code;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<Item> items = new ArrayList<>();

}
