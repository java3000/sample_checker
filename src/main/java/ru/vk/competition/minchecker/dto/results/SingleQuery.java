package ru.vk.competition.minchecker.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleQuery implements Query {
    public final String id;
    public final String query;

    public SingleQuery(@JsonProperty("id") String id,
                       @JsonProperty("query") String query) {
        this.id = id;
        this.query = query;
    }
}
