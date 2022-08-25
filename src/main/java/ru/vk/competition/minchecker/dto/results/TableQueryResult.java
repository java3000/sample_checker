package ru.vk.competition.minchecker.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TableQueryResult implements Result {

    Integer resultId;
    Integer code;
    TableQuery tableQuery;

    public TableQueryResult(
            @JsonProperty("resultId") Integer resultId,
            @JsonProperty("code") Integer code,
            @JsonProperty("tableQuery") TableQuery tableQuery
    ) {
        this.resultId = resultId;
        this.code = code;
        this.tableQuery = tableQuery;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public TableQuery getTableQuery() {
        return tableQuery;
    }

    public void setTableQuery(TableQuery tableQuery) {
        this.tableQuery = tableQuery;
    }
}
