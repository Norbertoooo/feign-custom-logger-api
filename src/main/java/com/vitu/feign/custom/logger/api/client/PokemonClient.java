package com.vitu.feign.custom.logger.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokemonClient", url = "${pokemon-api.url}")
public interface PokemonClient {

    @GetMapping("/{pokemonName}")
    ResponseEntity<Object> getPokemonByName(@PathVariable String pokemonName);

}
