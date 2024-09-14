package br.com.notacaoozona.notes.endpoint.mappers;

import br.com.notacaoozona.notes.domain.Note;
import br.com.notacaoozona.notes.endpoint.dto.NoteRequest;
import br.com.notacaoozona.notes.endpoint.dto.NoteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface NoteResponseMapper {

    NoteResponseMapper INSTANCE = Mappers.getMapper(NoteResponseMapper.class);

    NoteResponse map(Note note);

    Note map(NoteRequest noteRequest);
}
