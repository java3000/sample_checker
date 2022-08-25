package ru.vk.competition.minchecker.utils;

import ru.vk.competition.minchecker.dto.results.TableQuery;
import ru.vk.competition.minchecker.dto.results.TableQueryResult;

public class JsonBuilder {

    private static final String ADD_SINGLE_QUERY_RESULT_JSON = "{\"resultId\":%1$d,\"code\":%2$d}";
    private static final String ADD_TABLE_QUERY_RESULT_JSON = "{\"resultId\":%1$d, \"code\":%1$d, " +
            "\"tableQuery\" : { \"queryId\":%1$d, \"tableName\":%s, \"query\":%s\"}}";
    private static final String MODIFY_SINGLE_QUERY_RESULT_JSON = "{\"queryId\":%1$d,\"query\":%s}";
    private static final String MODIFY_TABLE_QUERY_RESULT_JSON = "{\"queryId\":%1$d, \"tableName\":%s, \"query\":%s}";

    public static String addSingleQueryResultJsonBuilder(Integer resultId, Integer code) {
        return String.format(ADD_SINGLE_QUERY_RESULT_JSON, resultId, code);
    }

    public static String addTableQueryResultJsonBuilder(Integer resultId, Integer code, TableQuery tableQuery) {
        return String.format(ADD_TABLE_QUERY_RESULT_JSON, resultId, code, tableQuery.queryId, tableQuery.tableName, tableQuery.query);
    }

    public static String addTableQueryResultJsonBuilder2(Integer resultId, Integer code) {
        return String.format(ADD_SINGLE_QUERY_RESULT_JSON, resultId, code);
    }

    public static String modifySingleQueryResultJsonBuilder(Integer queryid, String query) {
        return String.format(MODIFY_SINGLE_QUERY_RESULT_JSON, queryid, query);
    }

    public static String modifyTableQueryResultJsonBuilder(TableQuery tableQuery) {
        return String.format(MODIFY_TABLE_QUERY_RESULT_JSON, tableQuery.queryId, tableQuery.tableName, tableQuery.query);
    }

    public static String deleteSingleQueryResultJsonBuilder(Integer queryid, String query) {
        return String.format(MODIFY_SINGLE_QUERY_RESULT_JSON, queryid, query);
    }

    public static String deleteTableQueryResultJsonBuilder(Integer queryid, String query) {
        return String.format(MODIFY_SINGLE_QUERY_RESULT_JSON, queryid, query);
    }
}
