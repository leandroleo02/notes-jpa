package br.com.notacaoozona.notes.domain;

import br.com.notacaoozona.notes.infraestructure.persistence.model.NoteEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository {

    Optional<Note> findById(UUID id);

    List<Note> findAll();

    List<Note> findByCategory(String category);

    Note save(final Note note);

    Note update(final Note note);
}
