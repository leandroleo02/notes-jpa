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

    public List<Note> findByCategory(String category) {
        return noteRepository.findByCategory(category);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Transactional
    public Note create(Note noteToCreate) {
        return noteRepository.save(noteToCreate);
    }

    @Transactional
    public Note update(UUID id, Note noteToUpdate) {
        return noteRepository.update(noteToUpdate.withId(id));
    }
}
