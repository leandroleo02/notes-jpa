package br.com.notacaoozona.notes.endpoint;

import br.com.notacaoozona.notes.application.NoteService;
import br.com.notacaoozona.notes.endpoint.dto.NoteRequest;
import br.com.notacaoozona.notes.endpoint.dto.NoteResponse;
import br.com.notacaoozona.notes.endpoint.mappers.NoteResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("{id}")
    public NoteResponse findById(@PathVariable final UUID id) {
        return noteService.findById(id)
            .map(NoteResponseMapper.INSTANCE::map)
            .orElseThrow();
    }

    @GetMapping
    public List<NoteResponse> findAll() {
        return noteService.findAll()
            .stream()
            .map(NoteResponseMapper.INSTANCE::map)
            .toList();
    }
}
