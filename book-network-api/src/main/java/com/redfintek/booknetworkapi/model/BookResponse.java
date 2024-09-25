package com.redfintek.booknetworkapi.model;


import java.util.List;

public record BookResponse (
        String title,
        String isbn,
        List<Author>authors
){
}
