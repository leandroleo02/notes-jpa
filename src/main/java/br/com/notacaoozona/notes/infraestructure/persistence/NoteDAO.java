package br.com.notacaoozona.notes.infraestructure.persistence;

import br.com.notacaoozona.notes.infraestructure.persistence.model.NoteEntity;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface NoteDAO extends BaseJpaRepository<NoteEntity, UUID> {
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<NoteEntity> findAll();

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query(value = "SELECT * FROM notes n WHERE n.category = :category", nativeQuery = true)
    List<NoteEntity> findByCategory(String category);
}
