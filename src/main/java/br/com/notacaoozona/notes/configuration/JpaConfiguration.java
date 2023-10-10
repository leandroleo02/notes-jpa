package br.com.notacaoozona.notes.configuration;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(value = "br.com.notacaoozona.notes.infraestructure.persistence", repositoryBaseClass = BaseJpaRepositoryImpl.class)
public class JpaConfiguration {
}
