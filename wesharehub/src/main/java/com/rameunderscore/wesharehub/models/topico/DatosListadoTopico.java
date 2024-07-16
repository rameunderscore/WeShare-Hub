package com.rameunderscore.wesharehub.models.topico;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        Estado estado,
        String autor,
        String curso
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion().toString(),
                topico.getEstado(), topico.getAutor(), topico.getCurso());
    }
}
