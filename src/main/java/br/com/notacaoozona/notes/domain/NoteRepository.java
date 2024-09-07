package br.com.notacaoozona.notes.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository {

    Optional<Note> findById(UUID id);

    List<Note> findAll();

    Note save(final Note note);
}
