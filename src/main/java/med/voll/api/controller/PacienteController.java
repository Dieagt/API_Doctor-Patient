package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroPaciente datos) {

        repository.save(new Paciente(datos));
    }

    @GetMapping
    public Page<DatosListadoPaciente> listar(@PageableDefault (size = 2) Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosListadoPaciente::new);
    }

    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid DatosActualizarPaciente datos) {
        repository.getReferenceById(datos.id()).actualizar(datos);
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id){
        repository.deleteById(id);
    }
}
