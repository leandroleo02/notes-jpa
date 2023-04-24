package br.com.notacaoozona.notes.infraestructure.persistence;

import br.com.notacaoozona.notes.infraestructure.persistence.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface NoteDao extends JpaRepository<NoteEntity, UUID> {
}
