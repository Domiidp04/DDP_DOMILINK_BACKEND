package com.link.Domilink.infraestructure.controllers;

import com.link.Domilink.application.services.LinkService;
import com.link.Domilink.infraestructure.dtos.LinkRequestDto;
import com.link.Domilink.infraestructure.dtos.LinkResponseDto;
import com.link.Domilink.infraestructure.dtos.RedirectResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("links")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/{clientId}")
    public ResponseEntity<List<LinkResponseDto>> getByClientId(@PathVariable(name = "clientId") Long id) {
        return ResponseEntity.ok(linkService.getByClientId(id));
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<LinkResponseDto> createLink(
            @PathVariable(name = "clientId") Long clientId,
            @RequestBody LinkRequestDto linkRequestDto) {

        // Retornar la respuesta con el estado CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(linkService.createLink(clientId, linkRequestDto));
    }

    @DeleteMapping("/{linkId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "linkId") Long linkId){
        try {
            linkService.delete(linkId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }

    @GetMapping("/redirect/{shortUrl}")
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public RedirectResponseDto redirectToOriginalUrl(@PathVariable String shortUrl) {
        Optional<RedirectResponseDto> responseDto = linkService.getByShortUrl(shortUrl);
        return responseDto.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found"));
    }


}
