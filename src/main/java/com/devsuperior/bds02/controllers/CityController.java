package com.devsuperior.bds02.controllers;


import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/cities")
public class CityController {

    private CityService cityService;

    public CityController (CityService cityService){
        this.cityService = cityService;
    }


    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){

        List<CityDTO> cityDTO = cityService.findAll();

        return ResponseEntity.ok().body(cityDTO);

    }

    @PostMapping
    public ResponseEntity<CityDTO> insert (@RequestBody CityDTO cityDTO){

       cityDTO = cityService.insert(cityDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cityDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(cityDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
