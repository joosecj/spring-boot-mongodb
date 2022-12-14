package com.devsuperior.workshopmongo.dto;

import java.util.Date;

public class CommentDTO {

    private String text;
    private Date date;
    private AuthorDTO author;

    public CommentDTO() {
    }

    public CommentDTO(String text, Date date, AuthorDTO author) {
        this.text = text;
        this.date = date;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public AuthorDTO getAuthor() {
        return author;
    }
}
