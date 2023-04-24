package br.com.notacaoozona.notes.domain;

public record Note(String id,
           String title,
           String category,
           String text) {}
