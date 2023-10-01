package med.voll.api.paciente;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;

public record DatosRegistroPaciente(
        @NotBlank
        String nombre,
        @NotBlank
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        //@Pattern(regexp = "\\\\d{3}\\\\.?\\\\d{3}\\\\.?\\\\d{3}\\\\-?\\\\d{2}
        @Pattern (regexp = "[0-9]{4}")
        String documentoIdentidad,
        @NotNull
        @Valid
        DatosDireccion direccion) {

        public DatosRegistroPaciente(Paciente paciente) {
                this(
                        paciente.getNombre(),
                        paciente.getEmail(),
                        paciente.getTelefono(),
                        paciente.getDocumentoIdentidad(),
                        new DatosDireccion(
                                paciente.getDireccion().getCalle(),
                                paciente.getDireccion().getNumero(),
                                paciente.getDireccion().getComplemento(),
                                paciente.getDireccion().getDistrito(),
                                paciente.getDireccion().getCiudad()
                        )
                );
        }

}
