package br.com.notacaoozona.notes.endpoint;

import br.com.notacaoozona.notes.application.NoteService;
import br.com.notacaoozona.notes.endpoint.dto.NoteRequest;
import br.com.notacaoozona.notes.endpoint.dto.NoteResponse;
import br.com.notacaoozona.notes.endpoint.mappers.NoteResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor
@RequestMapping("notes")
@RestController
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public NoteResponse create(final @RequestBody NoteRequest noteRequest) {
        final var note = noteService.create(NoteResponseMapper.INSTANCE.map(noteRequest));
        return NoteResponseMapper.INSTANCE.map(note);
    }

    @PutMapping("{id}")
    public NoteResponse update(@PathVariable final UUID id, @RequestBody final NoteRequest noteRequest) {
        final var note = noteService.update(id, NoteResponseMapper.INSTANCE.map(noteRequest));
        return NoteResponseMapper.INSTANCE.map(note);
    }

    @GetMapping("{id}")
    public NoteResponse findById(@PathVariable final UUID id) {
        return noteService.findById(id)
            .map(NoteResponseMapper.INSTANCE::map)
            .orElseThrow();
    }

    @GetMapping("/find")
    public List<NoteResponse> findByCategory(@RequestParam("category") final String category) {
        return noteService.findByCategory(category).stream()
            .map(NoteResponseMapper.INSTANCE::map)
            .toList();
    }

    @GetMapping
    public List<NoteResponse> findAll() {
        return noteService.findAll()
            .stream()
            .map(NoteResponseMapper.INSTANCE::map)
            .toList();
    }
}
