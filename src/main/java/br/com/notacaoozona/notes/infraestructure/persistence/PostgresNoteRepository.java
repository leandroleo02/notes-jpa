package br.com.notacaoozona.notes.infraestructure.persistence;

import br.com.notacaoozona.notes.domain.Note;
import br.com.notacaoozona.notes.domain.NoteRepository;
import br.com.notacaoozona.notes.infraestructure.persistence.mappers.NoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostgresNoteRepository implements NoteRepository {

    private final NoteDAO dao;

    @Override
    public List<Note> findAll() {
        return dao.findAll()
            .stream().map(NoteMapper.INSTANCE::map)
            .toList();
    }

    @Override
    public Note save(Note note) {
        final var noteEntity = dao.save(NoteMapper.INSTANCE.map(note));
        return NoteMapper.INSTANCE.map(noteEntity);
    }
}
