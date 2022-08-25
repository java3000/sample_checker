package ru.vk.competition.minchecker.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TableQuery implements Query {
    Integer queryId;
    String tableName;
    String query;

    public TableQuery(@JsonProperty("queryId") Integer queryId,
                      @JsonProperty("tableName") String tableName,
                       @JsonProperty("query") String query) {
        this.queryId = queryId;
        this.tableName = tableName;
        this.query = query;
    }

    public Integer getQueryId() {
        return queryId;
    }

    public void setQueryId(Integer queryId) {
        this.queryId = queryId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
