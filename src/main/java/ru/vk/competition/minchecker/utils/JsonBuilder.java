package ru.vk.competition.minchecker.utils;

import ru.vk.competition.minchecker.dto.results.TableQuery;

public class JsonBuilder {

    // --- constanses ---

    //region SingleQuery
    private static final String ADD_SINGLE_QUERY_RESULT_JSON = "{\"resultId\":%1$d,\"code\":%2$d}";
    private static final String ADD_SINGLE_QUERY_JSON = "{\"queryId\":%1$d,\"query\":%s}";

    private static final String MODIFY_SINGLE_QUERY_RESULT_JSON = "{\"resultId\":%1$d,\"code\":%2$d}";
    private static final String MODIFY_SINGLE_QUERY_JSON = "{\"queryId\":%1$d,\"query\":%s}";

    private static final String DELETE_SINGLE_QUERY_RESULT_JSON = "{\"resultId\":%1$d,\"code\":%2$d}";

    private static final String GET_SINGLE_QUERY_RESULT_JSON = "{\"resultId\":%1$d,\"code\":%2$d,\'queryId:\'%3$d,\"query\":%s}";
    //endregion

    //region TableQuery
    private static final String ADD_TABLE_QUERY_RESULT_JSON = "{\"resultId\":%1$d, \"code\":%1$d, \"tableQuery\" : { \"queryId\":%1$d, \"tableName\":%s, \"query\":%s\"}}";
    private static final String MODIFY_TABLE_QUERY_RESULT_JSON = "{\"queryId\":%1$d, \"tableName\":%s, \"query\":%s}";
    private static final String GET_TABLE_QUERY_BY_ID_RESULT_JSON = "{\"resultId\":%1$d, \"code\":%1$d, \"tableQuery\": { \"queryId\":%1$d, \"tableName\":%s, \"query\":%s\"}}";
    private static final String EXECUTE_TABLE_QUERY_BY_ID_RESULT_JSON = "{\"resultId\":%1$d, \"code\":%1$d}";
    private static final String DELETE_TABLE_QUERY_BY_ID_RESULT_JSON = "{\"resultId\":%1$d, \"code\":%1$d}";
    private static final String MODIFY_TABLE_QUERY_BY_ID_RESULT_JSON = "{\"resultId\":%1$d, \"code\":%1$d}";
    //endregion

    //region Table
    public static final String ADD_TABLE_RESULT_JSON = "{\"resultId\":%1$d, \"code\":%1$d}";
    public static final String GET_TABLE_BY_NAME_RESULT_JSON = "{\"resultId\":%1$d, \"code\":%1$d, \"table:\"{ %s }}";
    public static final String DROP_TABLE_RESULT_JSON = "{\"resultId\":%1$d, \"code\":%1$d}";
    //endregion

    //region Report
    public static final String GET_REPORT_BY_ID_RESULT = "{\"resultId\":%1$d, \"code\":%1$d, \"getReport\": {%s}}";
    public static final String CREATE_REPORT_RESULT_JSON = "{\"resultId\":%1$d, \"code\":%1$d}";
    //endregion

    // --- methods ---

    //region SingleQuery
    public static String addSingleQueryResultJson (Integer resultId, Integer code) {
        return String.format(ADD_SINGLE_QUERY_RESULT_JSON, resultId, code);
    }
    public static String addSingleQueryJson (Integer queryId, String query) {
        return String.format(ADD_SINGLE_QUERY_JSON, queryId, query);
    }

    public static String modifySingleQueryResultJson (Integer resultId, Integer code) {
        return String.format(MODIFY_SINGLE_QUERY_RESULT_JSON, resultId, code);
    }

    public static String modifySingleQueryJson (Integer queryId, String query) {
        return String.format(MODIFY_SINGLE_QUERY_JSON, queryId, query);
    }

    public static String deleteSingleQueryResultJson (Integer resultId, Integer code) {
        return String.format(DELETE_SINGLE_QUERY_RESULT_JSON, resultId, code);
    }

    public static String getSingleQueryResultJson (Integer resultId, Integer code, Integer queryId, String query) {
        return String.format(GET_SINGLE_QUERY_RESULT_JSON, resultId, code, queryId, query);
    }
    //endregion

    //region TableQuery
    public static String addTableQueryResultJson (Integer resultId, Integer code, TableQuery tableQuery) {
        return String.format(ADD_TABLE_QUERY_RESULT_JSON, resultId, code, tableQuery.getQueryId(), tableQuery.getTableName(), tableQuery.getQuery());
    }

    public static String modifyTableQueryResultJson (TableQuery tableQuery) {
        return String.format(MODIFY_TABLE_QUERY_RESULT_JSON, tableQuery.getQueryId(), tableQuery.getTableName(), tableQuery.getQuery());
    }

    public static String getGetTableQueryByIdResultJson (Integer resultId, Integer code, TableQuery tableQuery) {
        return String.format(GET_TABLE_QUERY_BY_ID_RESULT_JSON, resultId, code, tableQuery.getQueryId(), tableQuery.getTableName(), tableQuery.getQuery());
    }

    public static String executeTableQueryByIdResultJson (Integer resultId, Integer code) {
        return String.format(EXECUTE_TABLE_QUERY_BY_ID_RESULT_JSON, resultId, code);
    }

    public static String deleteTableQueryByIdResultJson (Integer resultId, Integer code) {
        return String.format(DELETE_TABLE_QUERY_BY_ID_RESULT_JSON, resultId, code);
    }

    public static String modifyTableQueryByIdResultJson (Integer resultId, Integer code) {
        return String.format(MODIFY_TABLE_QUERY_BY_ID_RESULT_JSON, resultId, code);
    }
    //endregion

    public static String addTableResultJson (Integer resultId, Integer code) {
        return String.format(ADD_TABLE_RESULT_JSON, resultId, code);
    }

    public static String dropTableResultJson (Integer resultId, Integer code) {
        return String.format(DROP_TABLE_RESULT_JSON, resultId, code);
    }
    public static String createReportResultJson (Integer resultId, Integer code) {
        return String.format(CREATE_REPORT_RESULT_JSON, resultId, code);
    }

}
