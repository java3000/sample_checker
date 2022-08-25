package ru.vk.competition.minchecker.utils;

public interface Urls {

    String SINGLE_QUERY = "single-query/";
    String TABLE_QUERY = "table-query/";

    //region SingleQuery
    String ADD_NEW_QUERY_RESULT = "add-new-query-result";
    String ADD_SINGLE_QUERY = "add-new-query";

    String MODIFY_SINGLE_QUERY_RESULT = "add-modify-result";
    String MODIFY_SINGLE_QUERY = "modify-single-query";

    String DELETE_SINGLE_QUERY_RESULT = "add-delete-result";
    String DELETE_SINGLE_QUERY_BY_ID = "delete-single-query-by-id";

    String EXECUTE_SINGLE_QUERY_RESULT = "add-execute-result";
    String EXECUTE_SINGLE_QUERY_BY_ID = "execute-single-query-by-id";

    String GET_SINGLE_QUERY_BY_ID_RESULT = "add-get-single-query-by-id-result";
    String GET_SINGLE_QUERY_BY_ID = "get-single-query-by-id";

    String GET_ALL_SINGLE_QUERIES = "get-all-single-queries";
    //endregion

    //region TableQuery
    String ADD_NEW_TABLE_QUERY_RESULT = "add-new-query-to-table-result";
    String ADD_TABLE_QUERY = "add-new-query-to-table";

    String MODIFY_TABLE_QUERY_RESULT = "modify-table-query-by-id-result";
    String MODIFY_TABLE_QUERY = "modify-query-in-table";

    String DELETE_TABLE_QUERY_RESULT = "delete-table-query-by-id-result";
    String DELETE_TABLE_QUERY_BY_ID = "delete-table-query-by-id";

    String EXECUTE_TABLE_QUERY_RESULT = "execute-table-query-by-id-result";
    String EXECUTE_TABLE_QUERY_BY_ID = "execute-table-query-by-id";

    String GET_TABLE_QUERY_BY_ID_RESULT = "get-table-query-by-id-result";
    String GET_TABLE_QUERY_BY_ID = "get-table-query-by-id";

    String GET_ALL_TABLE_QUERY_BY_NAME = "get-all-queries-by-table-name";
    String GET_ALL_TABLE_QUERIES = "get-all-table-queries";
    //endregion

}
