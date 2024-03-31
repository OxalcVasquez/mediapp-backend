package com.jovf.mediappbackend.controller;

import com.jovf.mediappbackend.dto.ConsultaDTO;
import com.jovf.mediappbackend.dto.ConsultaListaExamenDTO;
import com.jovf.mediappbackend.exception.ModeloNotFoundException;
import com.jovf.mediappbackend.model.Consulta;
import com.jovf.mediappbackend.model.Examen;
import com.jovf.mediappbackend.service.IConsultaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService service;

    @Autowired
    private ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listar() throws Exception {
        List<ConsultaDTO> lista = service.listar().stream().map(p -> mapper.map(p,ConsultaDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> listarPorId(@PathVariable Integer id) throws Exception {
        Consulta obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        ConsultaDTO dto = mapper.map(obj,ConsultaDTO.class);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Consulta> registrar(@RequestBody Consulta p) throws Exception {
//        Consulta obj = service.registrar(p);
//        return new ResponseEntity<>(obj, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody ConsultaListaExamenDTO dto) throws Exception {
        Consulta c = mapper.map(dto.getConsulta(),Consulta.class);
        List<Examen> examenes = mapper.map(dto.getListExamen(),new TypeToken<List<Examen>>() {}.getType());
        Consulta obj = service.registrarTransaccional(c,examenes);
        // se quiere devolver la URL
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsulta()).toUri();
        return ResponseEntity.created(null).build();
    }

    @PutMapping
    public ResponseEntity<ConsultaDTO> modificar(@Valid @RequestBody ConsultaDTO dto) throws Exception {

        Consulta obj = service.listarPorId(dto.getIdConsulta());

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getIdConsulta());
        }

        Consulta p = mapper.map(dto,Consulta.class);
        Consulta pac = service.modificar(p);
        ConsultaDTO pacDTO = mapper.map(pac, ConsultaDTO.class);

        return new ResponseEntity<>(pacDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {
        Consulta obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("hateoas/{id}")
    public EntityModel<ConsultaDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
        Consulta obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO "+ id);
        }

        ConsultaDTO dto = mapper.map(obj, ConsultaDTO.class);

        EntityModel<ConsultaDTO> recurso = EntityModel.of(dto);

        //localhost:8080/consultas/1 - Link de forma informativa
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listarPorId(id));
        //this.getClass() => consulta controller
        recurso.add(link1.withRel("consulta-info"));
        return recurso;
    }


}
