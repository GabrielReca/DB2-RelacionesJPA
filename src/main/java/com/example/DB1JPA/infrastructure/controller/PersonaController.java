package com.example.DB1JPA.infrastructure.controller;

import com.example.DB1JPA.infrastructure.dto.input.PersonaInputDTO;
import com.example.DB1JPA.infrastructure.dto.output.PersonaOutputDTO;
import com.example.DB1JPA.application.port.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaService ps;

    @GetMapping("/busqueda/id/{id}")
    public ResponseEntity busquedaID(@PathVariable String id) throws Exception {
        PersonaOutputDTO personaOutputDTO;
        try{
            personaOutputDTO = ps.buscarPorID(id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("usuario puede ser nulo");
        }
        return ResponseEntity.ok(personaOutputDTO);
    }

    @GetMapping("/busqueda/todos")
    public ResponseEntity busquedaTodos() throws Exception {
        List<PersonaOutputDTO> listaOutput = ps.busquedaTodos();
        return ResponseEntity.ok(listaOutput);
    }

    @GetMapping("/busqueda/usuario/{usuario}")
    public ResponseEntity busquedaTodos(@PathVariable String usuario) throws Exception {
        PersonaOutputDTO personaOutputDTO;
        try{
            personaOutputDTO = ps.busquedaUsuario(usuario);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("usuario puede ser nulo");
        }
        return ResponseEntity.ok(personaOutputDTO);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity modificarPersona(@PathVariable String id, @RequestBody PersonaInputDTO personaDto) throws Exception
    {
        PersonaOutputDTO personaOutputDTO;
        try {
            personaOutputDTO = ps.modificarPersona(id, personaDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("usuario puede ser nulo");
        }
        return ResponseEntity.ok(personaOutputDTO);
    }

    @PostMapping("/anhadir")
    public ResponseEntity anhadirPersona(@RequestBody PersonaInputDTO personaInputDTO) {
        PersonaOutputDTO personaOutputDTO;
        try {
            personaOutputDTO = ps.anhadirPersona(personaInputDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("usuario puede ser nulo");
        }
        return ResponseEntity.ok(personaOutputDTO);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity borrarPersona(@PathVariable String id) throws Exception {
        PersonaOutputDTO personaOutputDTO = ps.eliminarUsuario(id);
        return ResponseEntity.ok(personaOutputDTO);
    }
}
