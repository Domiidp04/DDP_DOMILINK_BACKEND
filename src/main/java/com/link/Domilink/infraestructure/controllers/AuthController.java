package com.link.Domilink.infraestructure.controllers;

import com.link.Domilink.application.services.ClientService;
import com.link.Domilink.infraestructure.dtos.ClientRequestDto;
import com.link.Domilink.infraestructure.dtos.ClientResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = {"Authorization", "username", "id"})
public class AuthController {

    private final ClientService clientService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClientRequestDto clientRequestDto) {
        try {
            ClientResponseDto responseDto = clientService.register(clientRequestDto);
            return ResponseEntity.ok().build();  // Registro exitoso, devolver 200 OK
        } catch (IllegalArgumentException e) {
            // Manejar el caso en el que ya exista un usuario con el mismo username
            return ResponseEntity.badRequest().build();  // Devolver 400 Bad Request con el mensaje de error
        } catch (Exception e) {
            // Manejar cualquier otro error que ocurra
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ClientRequestDto clientRequestDto) {
        // Intentar hacer login
        boolean isAuthenticated = clientService.login(clientRequestDto);

        // Si las credenciales no son correctas, devolver 401 Unauthorized
        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Si el login es exitoso, codificar las credenciales en base64
        String username = clientRequestDto.username();
        String password = clientRequestDto.password();
        String credentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        // Agregar el encabezado 'Authorization' en la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + credentials);
        headers.set("username", clientRequestDto.username());
        headers.set("id", clientService.getClientIdByUsername(clientRequestDto.username()).toString());

        // Si el login es exitoso, devolver 200 OK
        return ResponseEntity.ok().headers(headers).build();
    }


}
