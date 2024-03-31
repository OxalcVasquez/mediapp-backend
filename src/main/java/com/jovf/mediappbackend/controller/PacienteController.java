package com.jovf.mediappbackend.controller;

import com.jovf.mediappbackend.exception.ModeloNotFoundException;
import com.jovf.mediappbackend.model.Paciente;
import com.jovf.mediappbackend.service.IPacienteService;
import jakarta.servlet.Servlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;


    @GetMapping
    public ResponseEntity<List<Paciente>> listar() throws Exception {
        List<Paciente> lista = service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listarPorId(@PathVariable Integer id) throws Exception {
        Paciente obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Paciente> registrar(@RequestBody Paciente p) throws Exception {
//        Paciente obj = service.registrar(p);
//        return new ResponseEntity<>(obj, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody Paciente p) throws Exception {
        Paciente obj = service.registrar(p);
        // se quiere devolver la URL
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPaciente()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Paciente> modificar(@RequestBody Paciente p) throws Exception {
        Paciente obj = service.listarPorId(p.getIdPaciente());

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + p.getIdPaciente());
        }

        Paciente pac = service.registrar(p);

        return new ResponseEntity<>(pac, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {
        Paciente obj = service.listarPorId(id);

        if(obj == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
