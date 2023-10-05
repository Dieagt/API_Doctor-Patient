package med.voll.api.paciente;

public record DatosListadoPaciente(
        Long id,
        DatosRegistroPaciente datosRegistroPaciente
) {

    public DatosListadoPaciente(Paciente paciente){
        this(
                paciente.getId(),
                new DatosRegistroPaciente(paciente)
        );
    }
}
