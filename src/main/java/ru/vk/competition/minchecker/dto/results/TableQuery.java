package ru.vk.competition.minchecker.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TableQuery implements Query {
    public final Integer queryId;
    public final String tableName;
    public final String query;

    public TableQuery(@JsonProperty("queryId") Integer queryId,
                      @JsonProperty("tableName") String tableName,
                       @JsonProperty("query") String query) {
        this.queryId = queryId;
        this.tableName = tableName;
        this.query = query;
    }
}
