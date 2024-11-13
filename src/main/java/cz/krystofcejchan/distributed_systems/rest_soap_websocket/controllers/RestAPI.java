package cz.krystofcejchan.distributed_systems.rest_soap_websocket.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPI {

    @GetMapping("/api/greet")
    public String greet() {
        return "Hello from REST API!";
    }
}
