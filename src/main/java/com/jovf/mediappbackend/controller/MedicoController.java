package com.jovf.mediappbackend.controller;

import com.jovf.mediappbackend.dto.MedicoDTO;
import com.jovf.mediappbackend.exception.ModeloNotFoundException;
import com.jovf.mediappbackend.model.Medico;
import com.jovf.mediappbackend.service.IMedicoService;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService service;

    @Autowired
    private ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listar() throws Exception {
        List<MedicoDTO> lista = service.listar().stream().map(p -> mapper.map(p,MedicoDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> listarPorId(@PathVariable Integer id) throws Exception {
        Medico obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        MedicoDTO dto = mapper.map(obj,MedicoDTO.class);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Medico> registrar(@RequestBody Medico p) throws Exception {
//        Medico obj = service.registrar(p);
//        return new ResponseEntity<>(obj, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody MedicoDTO dto) throws Exception {
        Medico p = mapper.map(dto,Medico.class);
        Medico obj = service.registrar(p);
        // se quiere devolver la URL
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedico()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<MedicoDTO> modificar(@Valid @RequestBody MedicoDTO dto) throws Exception {

        Medico obj = service.listarPorId(dto.getIdMedico());

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getIdMedico());
        }

        Medico p = mapper.map(dto,Medico.class);
        Medico pac = service.modificar(p);
        MedicoDTO pacDTO = mapper.map(pac, MedicoDTO.class);

        return new ResponseEntity<>(pacDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {
        Medico obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("hateoas/{id}")
    public EntityModel<MedicoDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
        Medico obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+ id);
        }

        MedicoDTO dto = mapper.map(obj, MedicoDTO.class);

        EntityModel<MedicoDTO> recurso = EntityModel.of(dto);

        //localhost:8080/medicos/1 - Link de forma informativa
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarPorId(id));
        //this.getClass() => medico controller
        recurso.add(link1.withRel("medico-info"));
        return recurso;
    }


}
