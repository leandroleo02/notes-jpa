package br.com.notacaoozona.notes.infraestructure.persistence.mappers;

import br.com.notacaoozona.notes.domain.Note;
import br.com.notacaoozona.notes.infraestructure.persistence.model.NoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoteMapper {

    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    Note map(NoteEntity noteEntity);

    NoteEntity map(Note note);
}
