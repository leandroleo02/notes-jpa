package br.com.notacaoozona.notes.infraestructure.persistence;

import br.com.notacaoozona.notes.domain.Note;
import br.com.notacaoozona.notes.domain.NoteRepository;
import br.com.notacaoozona.notes.infraestructure.persistence.mappers.NoteMapper;
import br.com.notacaoozona.notes.infraestructure.persistence.model.NoteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.StreamSupport.stream;

@RequiredArgsConstructor
@Repository
public class PostgresNoteRepository implements NoteRepository {

    private final NoteDAO dao;

    @Override
    public List<Note> findAll() {
        return stream(dao.findAll(Example.of(new NoteEntity())).spliterator(), false)
            .map(NoteMapper.INSTANCE::map)
            .toList();
    }

    @Override
    public Note save(Note note) {
        final var noteEntity = dao.persist(NoteMapper.INSTANCE.map(note));
        return NoteMapper.INSTANCE.map(noteEntity);
    }
}
