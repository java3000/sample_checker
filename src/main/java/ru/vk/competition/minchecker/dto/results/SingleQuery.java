package ru.vk.competition.minchecker.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleQuery implements Query {
    Integer id;
    String query;

    public SingleQuery(@JsonProperty("id") Integer id,
                       @JsonProperty("query") String query) {
        this.id = id;
        this.query = query;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
