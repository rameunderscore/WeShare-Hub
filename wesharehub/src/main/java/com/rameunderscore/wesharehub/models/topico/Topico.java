package com.rameunderscore.wesharehub.models.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String autor;
    private String curso;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.estado = datosRegistroTopico.estado();
        this.autor = datosRegistroTopico.autor();
        this.curso = datosRegistroTopico.nombreCurso();
    }

    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
        this.autor = datosActualizarTopico.autor();
        this.mensaje = datosActualizarTopico.mensaje();
        this.curso = datosActualizarTopico.nombreCurso();
        this.titulo = datosActualizarTopico.titulo();
        this.estado = datosActualizarTopico.estado();
        this.fechaCreacion = LocalDateTime.now();
    }
}
