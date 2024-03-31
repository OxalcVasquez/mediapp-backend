package com.jovf.mediappbackend.controller;

import com.jovf.mediappbackend.dto.ExamenDTO;
import com.jovf.mediappbackend.exception.ModeloNotFoundException;
import com.jovf.mediappbackend.model.Examen;
import com.jovf.mediappbackend.service.IExamenService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/examenes")
public class ExamenController {

    @Autowired
    private IExamenService service;

    @Autowired
    private ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<ExamenDTO>> listar() throws Exception {
        List<ExamenDTO> lista = service.listar().stream().map(p -> mapper.map(p,ExamenDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamenDTO> listarPorId(@PathVariable Integer id) throws Exception {
        Examen obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        ExamenDTO dto = mapper.map(obj,ExamenDTO.class);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Examen> registrar(@RequestBody Examen p) throws Exception {
//        Examen obj = service.registrar(p);
//        return new ResponseEntity<>(obj, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody ExamenDTO dto) throws Exception {
        Examen p = mapper.map(dto,Examen.class);
        Examen obj = service.registrar(p);
        // se quiere devolver la URL
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExamen()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<ExamenDTO> modificar(@Valid @RequestBody ExamenDTO dto) throws Exception {

        Examen obj = service.listarPorId(dto.getIdExamen());

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getIdExamen());
        }

        Examen p = mapper.map(dto,Examen.class);
        Examen pac = service.modificar(p);
        ExamenDTO pacDTO = mapper.map(pac, ExamenDTO.class);

        return new ResponseEntity<>(pacDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {
        Examen obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("hateoas/{id}")
    public EntityModel<ExamenDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
        Examen obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+ id);
        }

        ExamenDTO dto = mapper.map(obj, ExamenDTO.class);

        EntityModel<ExamenDTO> recurso = EntityModel.of(dto);

        //localhost:8080/examens/1 - Link de forma informativa
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarPorId(id));
        //this.getClass() => examen controller
        recurso.add(link1.withRel("examen-info"));
        return recurso;
    }


}
