package com.jovf.mediappbackend.controller;

import com.jovf.mediappbackend.dto.EspecialidadDTO;
import com.jovf.mediappbackend.exception.ModeloNotFoundException;
import com.jovf.mediappbackend.model.Especialidad;
import com.jovf.mediappbackend.service.IEspecialidadService;
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
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private IEspecialidadService service;

    @Autowired
    private ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<EspecialidadDTO>> listar() throws Exception {
        List<EspecialidadDTO> lista = service.listar().stream().map(p -> mapper.map(p,EspecialidadDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDTO> listarPorId(@PathVariable Integer id) throws Exception {
        Especialidad obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        EspecialidadDTO dto = mapper.map(obj,EspecialidadDTO.class);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Especialidad> registrar(@RequestBody Especialidad p) throws Exception {
//        Especialidad obj = service.registrar(p);
//        return new ResponseEntity<>(obj, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody EspecialidadDTO dto) throws Exception {
        Especialidad p = mapper.map(dto,Especialidad.class);
        Especialidad obj = service.registrar(p);
        // se quiere devolver la URL
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEspecialidad()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<EspecialidadDTO> modificar(@Valid @RequestBody EspecialidadDTO dto) throws Exception {

        Especialidad obj = service.listarPorId(dto.getIdEspecialidad());

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getIdEspecialidad());
        }

        Especialidad p = mapper.map(dto,Especialidad.class);
        Especialidad pac = service.modificar(p);
        EspecialidadDTO pacDTO = mapper.map(pac, EspecialidadDTO.class);

        return new ResponseEntity<>(pacDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {
        Especialidad obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("hateoas/{id}")
    public EntityModel<EspecialidadDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
        Especialidad obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+ id);
        }

        EspecialidadDTO dto = mapper.map(obj, EspecialidadDTO.class);

        EntityModel<EspecialidadDTO> recurso = EntityModel.of(dto);

        //localhost:8080/especialidads/1 - Link de forma informativa
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarPorId(id));
        //this.getClass() => especialidad controller
        recurso.add(link1.withRel("especialidad-info"));
        return recurso;
    }


}
