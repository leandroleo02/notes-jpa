package br.com.notacaoozona.notes.infraestructure.persistence;

import br.com.notacaoozona.notes.infraestructure.persistence.model.NoteEntity;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;

import java.util.UUID;

interface NoteDAO extends BaseJpaRepository<NoteEntity, UUID> {
}
