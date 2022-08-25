package ru.vk.competition.minchecker.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TableQueryResult {

    public final Integer resultId;
    public final Integer code;
    public final TableQuery tableQuery;

    public TableQueryResult(
            @JsonProperty("resultId") Integer resultId,
            @JsonProperty("code") Integer code,
            @JsonProperty("tableQuery") TableQuery tableQuery
    ) {
        this.resultId = resultId;
        this.code = code;
        this.tableQuery = tableQuery;
    }
}
