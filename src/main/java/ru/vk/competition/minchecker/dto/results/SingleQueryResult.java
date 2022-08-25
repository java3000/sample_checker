package ru.vk.competition.minchecker.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleQueryResult implements Result {

    Integer resultId;
    Integer code;

    public SingleQueryResult(
            @JsonProperty("resultId") Integer resultId,
            @JsonProperty("code") Integer code
    ) {
        this.resultId = resultId;
        this.code = code;
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
}
