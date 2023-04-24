package br.com.notacaoozona.notes.endpoint.dto;

public record NoteResponse(String id,
                           String title,
                           String category,
                           String text) {
}
