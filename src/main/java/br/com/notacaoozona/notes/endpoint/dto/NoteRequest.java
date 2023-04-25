package br.com.notacaoozona.notes.endpoint.dto;

public record NoteRequest(String title,
                          String category,
                          String text) {
}
