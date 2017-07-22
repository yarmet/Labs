package com.company.webservice.entities;

import lombok.Data;
import com.company.webservice.EndpointUrl;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khamedov on 11.07.2017.
 */

@Data
@XmlRootElement(name = "itemRequest", namespace = EndpointUrl.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"items"})
public class ItemRequest {

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<Item> items = new ArrayList<>();

}
