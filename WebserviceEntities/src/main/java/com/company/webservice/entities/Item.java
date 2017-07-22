package com.company.webservice.entities;

import com.company.webservice.EndpointUrl;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * Created by ruslan on 17.07.2017.
 */

@Data
@XmlType(name = "")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = EndpointUrl.NAMESPACE_URI)
public class Item {

    @XmlAttribute(name = "time", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date time;

    @XmlAttribute(name = "value", required = true)
    protected double value;


    @XmlAttribute(name = "deviceId", required = true)
    protected long deviceId;

    @XmlAttribute(name = "parameterId", required = true)
    protected String parameterId;


}
