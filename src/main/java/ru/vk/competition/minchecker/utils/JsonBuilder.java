package ru.vk.competition.minchecker.utils;

public class JsonBuilder {

    private static final String ADD_SINGLE_QUERY_RESULT_JSON = "{\"resultId\":%1$d,\"code\":%2$d}";
    private static final String MODIFY_SINGLE_QUERY_RESULT_JSON = "{\"queryId\":%s,\"query\":%s}";

    public static String addSingleQueryResultJsonBuilder(Integer resultId, Integer code) {
        return String.format(ADD_SINGLE_QUERY_RESULT_JSON, resultId, code);
    }

    public static String modifySingleQueryResultJsonBuilder(String queryid, String query) {
        return String.format(MODIFY_SINGLE_QUERY_RESULT_JSON, queryid, query);
    }
}
