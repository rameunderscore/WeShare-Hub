package com.rameunderscore.wesharehub.models.topico;

public record DatosRespuestaTopico (
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso
){
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getAutor(), topico.getMensaje(),
                topico.getCurso(), topico.getTitulo());
    }
}
