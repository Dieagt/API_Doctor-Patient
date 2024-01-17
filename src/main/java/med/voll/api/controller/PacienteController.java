package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPaciente> registrar(@RequestBody @Valid DatosRegistroPaciente datos, UriComponentsBuilder uriComponentsBuilder) {
        Paciente paciente = repository.save(new Paciente(datos));
        DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(paciente);
        URI url = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPaciente>> listar(@PageableDefault (size = 2) Pageable paginacion) {
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosListadoPaciente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarPaciente datos) {
        Paciente paciente = repository.getReferenceById(datos.id());
        paciente.actualizar(datos);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente));
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DatosRespuestaPaciente> retornar(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente));
    }
}
