package ru.vk.competition.minchecker.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleQuery {
    public final Integer id;
    public final String query;

    public SingleQuery(@JsonProperty("id") Integer id,
                       @JsonProperty("query") String query) {
        this.id = id;
        this.query = query;
    }
}
