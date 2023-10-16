package br.com.notacaoozona.notes.domain;

import java.util.UUID;

public record Note(UUID id,
                   String title,
                   String category,
                   String text) {

    public Note(String title, String category, String text) {
        this(UUID.randomUUID(), title, category, text);
    }
}
