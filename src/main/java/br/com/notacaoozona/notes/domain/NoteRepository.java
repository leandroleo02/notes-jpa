package br.com.notacaoozona.notes.domain;

import java.util.List;

public interface NoteRepository {

    List<Note> findAll();
}
