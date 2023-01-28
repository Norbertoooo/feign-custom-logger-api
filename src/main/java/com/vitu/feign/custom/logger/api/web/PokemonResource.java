package com.vitu.feign.custom.logger.api.web;

import com.vitu.feign.custom.logger.api.client.PokemonClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemon")
public class PokemonResource {

    private final PokemonClient pokemonClient;

    @GetMapping()
    public ResponseEntity<Object> getPokemonByName(@RequestHeader String pokemonName) {
        ResponseEntity<Object> pokemonByName = pokemonClient.getPokemonByName(pokemonName);
        return ResponseEntity.ok(pokemonByName.getBody());
    }
}
