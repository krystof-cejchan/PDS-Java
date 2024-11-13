package cz.krystofcejchan.distributed_systems.rest_soap_websocket.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.stereotype.Service;


@WebService
@Service
public class SoapService {

    @WebMethod
    public String getGreeting(String name) {
        return "Hello, " + name + "! This is a SOAP response.";
    }
}
