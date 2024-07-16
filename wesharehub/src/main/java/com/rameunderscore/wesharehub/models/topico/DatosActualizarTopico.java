package com.rameunderscore.wesharehub.models.topico;

public record DatosActualizarTopico(
        String autor,
        String mensaje,
        String nombreCurso,
        String titulo,
        Estado estado
) {
}
