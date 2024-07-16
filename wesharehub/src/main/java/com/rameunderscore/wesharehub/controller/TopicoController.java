package com.rameunderscore.wesharehub.controller;

import com.rameunderscore.wesharehub.models.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;

    // Crear topico
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);

    }

    // Listar topicos
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(@PageableDefault(page=0, size=10, sort = {"fechaCreacion"}) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    // Listar topico con Id
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosListadoTopico> listarTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topicoEncontrado = Optional.of(topicoRepository.getReferenceById(id));
        if (topicoEncontrado.isPresent()) {
            return ResponseEntity.ok(new DatosListadoTopico(topicoEncontrado.get()));
        }
        return ResponseEntity.notFound().build();
    }

    // Actualizar topico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id,  @RequestBody DatosActualizarTopico datosActualizarTopico) {
        Optional<Topico> topicoBuscado = Optional.of(topicoRepository.getReferenceById(id));
        if (topicoBuscado.isPresent()) {
            Topico topicoEncontrado = topicoBuscado.get();
            topicoEncontrado.actualizarTopico(datosActualizarTopico);
            return ResponseEntity.ok(new DatosRespuestaTopico(topicoEncontrado));
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar topico - mejores practicas
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topicoBuscado = Optional.of(topicoRepository.getReferenceById(id));
        if (topicoBuscado.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
