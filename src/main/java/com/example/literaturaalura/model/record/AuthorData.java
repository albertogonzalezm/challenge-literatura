package com.example.literaturaalura.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorData(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Integer birthYear,
        @JsonAlias("death_year") Integer deathYear
) {
}
