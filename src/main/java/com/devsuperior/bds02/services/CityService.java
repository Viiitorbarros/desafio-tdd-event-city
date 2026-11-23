package com.devsuperior.bds02.services;


import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.excecao.DatabaseException;
import com.devsuperior.bds02.repositories.CityRespository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final HttpServletResponse httpServletResponse;
    private CityRespository cityRespository;

    public CityService(CityRespository cityRespository, HttpServletResponse httpServletResponse){
        this.cityRespository = cityRespository;
        this.httpServletResponse = httpServletResponse;
    }


    public List<CityDTO> findAll(){
        List<City> listCity = cityRespository.findAll(Sort.by("name"));

        return listCity.stream().map(x-> new CityDTO(x)).toList();
    }

    public CityDTO insert (CityDTO cityDTO){

        City entity = new City();

        entity.setName(cityDTO.getName());

        entity = cityRespository.save(entity);

        return new CityDTO(entity);

    }


    public ResponseEntity delete(Long id){

        if (!cityRespository.existsById(id)) {
            ResponseEntity.notFound();
        }

        try {

            cityRespository.deleteById(id);

        }catch (DataIntegrityViolationException e){

            throw new DatabaseException("Falha na integridade"));

        }


    }



}
