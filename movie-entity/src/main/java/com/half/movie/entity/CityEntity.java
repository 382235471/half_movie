package com.half.movie.entity;

import lombok.Data;

@Data
public class CityEntity {

    Integer id;
    String name;
    String letter;
    Integer hot;

    public CityEntity(Integer id, String name, String letter, Integer hot) {
        this.id = id;
        this.name = name;
        this.letter = letter;
        this.hot = hot;
    }
}
