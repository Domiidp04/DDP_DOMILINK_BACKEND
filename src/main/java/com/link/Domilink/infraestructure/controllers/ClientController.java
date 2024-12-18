package com.link.Domilink.infraestructure.controllers;

import com.link.Domilink.application.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = {"Authorization", "username", "id"})

public class ClientController {

    private final ClientService clientService;

//    @GetMapping
//    public ResponseEntity<List<ClientResponseDto>> getAll(){
//        return ResponseEntity.ok(clientService.getAll());
//    }
//
//    @GetMapping("/{clientId}")
//    public ResponseEntity<Optional<ClientResponseDto>> getById(@PathVariable(name = "clientId") Long clientId){
//        return ResponseEntity.ok(clientService.getById(clientId));
//    }


}
