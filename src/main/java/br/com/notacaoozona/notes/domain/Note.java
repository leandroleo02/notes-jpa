package br.com.notacaoozona.notes.domain;

public record Note(String id,
           String title,
           String category,
           String text) {

    public Note(String title, String category, String text) {
        this(null, title, category, text);
    }
}
