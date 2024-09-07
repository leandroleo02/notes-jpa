package br.com.notacaoozona.notes.application;

import br.com.notacaoozona.notes.domain.Note;
import br.com.notacaoozona.notes.domain.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public Optional<Note> findById(UUID id) {
        return noteRepository.findById(id);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Transactional
    public Note create(Note noteForCreate) {
        return noteRepository.save(noteForCreate);
    }
}
