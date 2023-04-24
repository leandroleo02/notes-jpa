package br.com.notacaoozona.notes.infraestructure.persistence;

import br.com.notacaoozona.notes.domain.Note;
import br.com.notacaoozona.notes.domain.NoteRepository;
import br.com.notacaoozona.notes.infraestructure.persistence.mappers.NoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class NoteRepositoryImpl implements NoteRepository {

    private final NoteDao dao;

    @Override
    public List<Note> findAll() {
        return dao.findAll()
            .stream().map(NoteMapper.INSTANCE::map)
            .toList();
    }
}
