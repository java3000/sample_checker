package ru.vk.competition.minchecker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.vk.competition.minchecker.dto.results.SingleQueryResult;
import ru.vk.competition.minchecker.dto.results.TableQuery;
import ru.vk.competition.minchecker.dto.results.TableQueryResult;
import ru.vk.competition.minchecker.utils.JsonBuilder;
import ru.vk.competition.minchecker.utils.RequestBuilder;
import ru.vk.competition.minchecker.utils.SystemVariables;
import ru.vk.competition.minchecker.utils.Urls;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartService {

    OkHttpClient client = new OkHttpClient();
    public static final String API_ROOT = new SystemVariables().apiRoot();

    @NotNull
    private SingleQueryResult getSingleQueryResult(int i, int i2, String uriType, String uriMethod) throws IOException {
        SingleQueryResult singleQueryResult = new SingleQueryResult(i, i2);
        String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
        RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
        Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + uriType + uriMethod, addSingleQueryResultRequestBody);
        System.out.println(addNewSingleQueryResult.url());
        Call call = client.newCall(addNewSingleQueryResult);
        Response response = call.execute();
        return singleQueryResult;
    }

    @NotNull
    private TableQueryResult getTableQueryResult(int id, String tableName, String query, int code, String uriType, String uriMethod) throws IOException {
        TableQuery tableQuery = new TableQuery(id, tableName, query);
        TableQueryResult tableQueryResult = new TableQueryResult(id, code, tableQuery);
        String tableQueryResultJson = JsonBuilder.addTableQueryResultJsonBuilder(tableQueryResult.resultId, tableQueryResult.code, tableQueryResult.tableQuery);
        RequestBody addTableQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), tableQueryResultJson);
        Request addNewTableQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + uriType + uriMethod, addTableQueryResultRequestBody);
        System.out.println(addNewTableQueryResult.url());
        Call call = client.newCall(addNewTableQueryResult);
        Response response = call.execute();
        return tableQueryResult;
    }

    @NotNull
    private TableQueryResult getTableQueryResultModify(int i, int i2, String tableName, String query, String uriType, String uriMethod) throws IOException {
        TableQuery tableQuery = new TableQuery(i, tableName, query);
        TableQueryResult tableQueryResult = new TableQueryResult(i, i2, tableQuery);
        String tableQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(tableQueryResult.resultId, tableQueryResult.code);
        RequestBody addTableQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), tableQueryResultJson);
        Request addNewTableQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + uriType + uriMethod, addTableQueryResultRequestBody);
        System.out.println(addNewTableQueryResult.url());
        Call call = client.newCall(addNewTableQueryResult);
        Response response = call.execute();
        return tableQueryResult;
    }

    private void sendPostSingleQueryRequest(SingleQueryResult singleQueryResult, int s, String s2, String uriType, String uriMethod) throws IOException {
        HttpUrl url = HttpUrl.parse(API_ROOT + uriType + uriMethod).newBuilder()
                .addQueryParameter("resultId", String.valueOf(singleQueryResult.resultId))
                .build();

        String bodyJson = JsonBuilder.modifySingleQueryResultJsonBuilder(s, s2);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyJson);
        Request request = RequestBuilder.postRequestBuilder(url.toString(), body);

        Call call1 = client.newCall(request);
        Response response1 = call1.execute();
        System.out.println(response1.code());
    }

    private void sendPostTableQueryRequest(TableQueryResult tableQueryResult, String uriType, String uriMethod) throws IOException {
        HttpUrl url = HttpUrl.parse(API_ROOT + uriType + uriMethod).newBuilder()
                .addQueryParameter("resultId", String.valueOf(tableQueryResult.resultId))
                .build();

        String bodyJson = JsonBuilder.addTableQueryResultJsonBuilder(tableQueryResult.resultId, tableQueryResult.code, tableQueryResult.tableQuery);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyJson);
        Request request = RequestBuilder.postRequestBuilder(url.toString(), body);

        Call call1 = client.newCall(request);
        Response response1 = call1.execute();
        System.out.println(response1.code());
    }

    private void sendPutSingleQueryRequest(SingleQueryResult singleQueryResult, int s, String s2, String uriType, String uriMethod) throws IOException {
        HttpUrl url = HttpUrl.parse(API_ROOT + uriType + uriMethod).newBuilder()
                .addQueryParameter("resultId", singleQueryResult.resultId.toString())
                .build();

        String bodyJson = JsonBuilder.modifySingleQueryResultJsonBuilder(s, s2);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyJson);
        Request request = RequestBuilder.putRequestBuilder(url.toString(), body);

        Call call1 = client.newCall(request);
        Response response1 = call1.execute();
        System.out.println(response1.code());
    }

    private void sendPutTableQueryRequest(TableQueryResult tableQueryResult, TableQuery tableQuery, String uriType, String uriMethod) throws IOException {
        HttpUrl url = HttpUrl.parse(API_ROOT + uriType + uriMethod).newBuilder()
                .addQueryParameter("resultId", tableQueryResult.resultId.toString())
                .build();

        String bodyJson = JsonBuilder.modifyTableQueryResultJsonBuilder(tableQuery);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyJson);
        Request request = RequestBuilder.putRequestBuilder(url.toString(), body);

        Call call1 = client.newCall(request);
        Response response1 = call1.execute();
        System.out.println(response1.code());
    }

    private void sendDeleteSingleQueryRequest(SingleQueryResult singleQueryResult, int s, String s2, String uriType, String uriMethod) throws IOException {
        HttpUrl url = HttpUrl.parse(API_ROOT + uriType + uriMethod + "/" + s).newBuilder()
                .addQueryParameter("resultId", singleQueryResult.resultId.toString())
                .build();

        String bodyJson = JsonBuilder.modifySingleQueryResultJsonBuilder(s, s2);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyJson);
        Request request = RequestBuilder.deleteRequestBuilder(url.toString(), body);

        Call call1 = client.newCall(request);
        Response response1 = call1.execute();
        System.out.println(response1.code());
    }

    private void sendDeleteTableQueryRequest(TableQueryResult tableQueryResult, int s, String s2, String uriType, String uriMethod) throws IOException {
        HttpUrl url = HttpUrl.parse(API_ROOT + uriType + uriMethod + "/" + s).newBuilder()
                .addQueryParameter("resultId", tableQueryResult.resultId.toString())
                .build();

        String bodyJson = JsonBuilder.deleteTableQueryResultJsonBuilder(s, s2);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyJson);
        Request request = RequestBuilder.deleteRequestBuilder(url.toString(), body);

        Call call1 = client.newCall(request);
        Response response1 = call1.execute();
        System.out.println(response1.code());
    }

    private void sendGetSingleQueryRequest(SingleQueryResult singleQueryResult, int s, String s2, String uriType, String uriMethod) throws IOException {
        HttpUrl url = HttpUrl.parse(API_ROOT + uriType + uriMethod + "/" + s).newBuilder()
                .addQueryParameter("resultId", singleQueryResult.resultId.toString())
                .build();
        Request request = RequestBuilder.getRequestBuilder(url.toString());

        Call call1 = client.newCall(request);
        Response response1 = call1.execute();
        System.out.println(response1.code());
    }

    public void onStartMission() {
        try {
            // --- ADD ---
            try {
                SingleQueryResult singleQueryResult1 = getSingleQueryResult(10, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
                sendPostSingleQueryRequest(singleQueryResult1, Integer.MAX_VALUE, "select", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

                SingleQueryResult singleQueryResult2 = getSingleQueryResult(11, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
                sendPostSingleQueryRequest(singleQueryResult2, Integer.MAX_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

                SingleQueryResult singleQueryResult3 = getSingleQueryResult(12, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
                sendPostSingleQueryRequest(singleQueryResult3, 15, "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

                SingleQueryResult singleQueryResult7 = getSingleQueryResult(16, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
                sendPostSingleQueryRequest(singleQueryResult7, Integer.MIN_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

                SingleQueryResult singleQueryResult8 = getSingleQueryResult(17, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
                sendPostSingleQueryRequest(singleQueryResult8, 3221, ";--select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

                SingleQueryResult singleQueryResult9 = getSingleQueryResult(18, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
                sendPostSingleQueryRequest(singleQueryResult9, 3321, ";select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

                SingleQueryResult singleQueryResult10 = getSingleQueryResult(19, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
                sendPostSingleQueryRequest(singleQueryResult10, 3421, "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // --- MODIFY ---
            try {
                SingleQueryResult singleQueryResult21 = getSingleQueryResult(20, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult21, Integer.MAX_VALUE, "select", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

                SingleQueryResult singleQueryResult22 = getSingleQueryResult(21, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult22, Integer.MAX_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

                SingleQueryResult singleQueryResult23 = getSingleQueryResult(22, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult23, 15, "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

                SingleQueryResult singleQueryResult27 = getSingleQueryResult(26, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult27, Integer.MIN_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

                SingleQueryResult singleQueryResult28 = getSingleQueryResult(27, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult28, 3221, ";--select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

                SingleQueryResult singleQueryResult29 = getSingleQueryResult(28, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult29, 3321, ";select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

                SingleQueryResult singleQueryResult20 = getSingleQueryResult(29, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult20, 3421, "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // --- EXECUTE ---
            try {
                SingleQueryResult singleQueryResult1 = getSingleQueryResult(40, 406, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult1, Integer.MAX_VALUE, "select", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult2 = getSingleQueryResult(41, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult2, Integer.MAX_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult3 = getSingleQueryResult(42, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult3, 15, "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult7 = getSingleQueryResult(46, 406, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult7, Integer.MIN_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult8 = getSingleQueryResult(47, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult8, 3221, ";--select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult9 = getSingleQueryResult(48, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult9, 3321, ";select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult10 = getSingleQueryResult(49, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult10, 3421, "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // --- DELETE ---
            try {
                SingleQueryResult singleQueryResult1 = getSingleQueryResult(30, 406, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult1, Integer.MAX_VALUE, "select", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult2 = getSingleQueryResult(31, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult2, Integer.MAX_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult3 = getSingleQueryResult(32, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult3, 15, "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult7 = getSingleQueryResult(36, 406, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult7, Integer.MIN_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult8 = getSingleQueryResult(37, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult8, 3221, ";--select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult9 = getSingleQueryResult(38, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult9, 3321, ";select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

                SingleQueryResult singleQueryResult10 = getSingleQueryResult(39, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult10, 3421, "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // --- GET ---
            try {
                SingleQueryResult singleQueryResult = new SingleQueryResult(50, 500);
                String singleQueryResultJson = String.format("{\"resultId:\"%1$d, \"code\":%1$d, \"queryId\":%1$d, \"query\":%s}",
                        singleQueryResult.resultId, singleQueryResult.code, 3, "select * from Customer");
                RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
                Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + Urls.SINGLE_QUERY + Urls.GET_SINGLE_QUERY_BY_ID_RESULT, addSingleQueryResultRequestBody);
                System.out.println(addNewSingleQueryResult.url());
                Call call = client.newCall(addNewSingleQueryResult);
                Response response = call.execute();

                HttpUrl url = HttpUrl.parse(API_ROOT + Urls.SINGLE_QUERY + Urls.GET_SINGLE_QUERY_BY_ID + "/" + 3).newBuilder()
                        .addQueryParameter("resultId", String.valueOf(singleQueryResult.resultId))
                        .build();
                Request request = RequestBuilder.getRequestBuilder(url.toString());

                Call call1 = client.newCall(request);
                Response response1 = call1.execute();
                System.out.println(response1.code());

            } catch (Exception e) {
                e.printStackTrace();
            }

            // --- GET ALL---
            try {
                SingleQueryResult singleQueryResult = new SingleQueryResult(60, 200);
                String singleQueryResultJson = String.format("{\"resultId:\"%1$d, \"code\":%1$d}", singleQueryResult.resultId, singleQueryResult.code);
                RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
                Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + Urls.SINGLE_QUERY + Urls.GET_ALL_SINGLE_QUERIES, addSingleQueryResultRequestBody);
                System.out.println(addNewSingleQueryResult.url());
                Call call = client.newCall(addNewSingleQueryResult);
                Response response = call.execute();

                HttpUrl url = HttpUrl.parse(API_ROOT + Urls.SINGLE_QUERY + Urls.GET_ALL_SINGLE_QUERIES).newBuilder()
                        .addQueryParameter("resultId", String.valueOf(singleQueryResult.resultId))
                        .build();
                Request request = RequestBuilder.getRequestBuilder(url.toString());

                Call call1 = client.newCall(request);
                Response response1 = call1.execute();
                System.out.println(response1.code());

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission2() {
        try {

            //region ADD
            TableQueryResult tableQueryResult1 = getTableQueryResult(110,"Customer", "select", 406, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostTableQueryRequest(tableQueryResult1, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            TableQueryResult tableQueryResult2 = getTableQueryResult(111,"Customer", "select * from Customer", 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostTableQueryRequest(tableQueryResult2, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            TableQueryResult tableQueryResult3 = getTableQueryResult(112,"Customer", "select \\u002A from Customer", 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostTableQueryRequest(tableQueryResult3, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            TableQueryResult tableQueryResult4 = getTableQueryResult(113,"Customer", "select * from Customer", 406, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostTableQueryRequest(tableQueryResult4, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            TableQueryResult tableQueryResult5 = getTableQueryResult(114,"Customer", ";--select * from Customer", 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostTableQueryRequest(tableQueryResult5, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            TableQueryResult tableQueryResult6 = getTableQueryResult(115,"Customer", ";select * from Customer", 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostTableQueryRequest(tableQueryResult6, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            TableQueryResult tableQueryResult7 = getTableQueryResult(116,"Customer", "if exists(select * from Customer)", 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostTableQueryRequest(tableQueryResult7, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);
            //endregion

            //region MODIFY
            TableQueryResult tableQueryResult21 = getTableQueryResultModify(220,406,"Customer", "select",  Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult21, tableQueryResult21.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            TableQueryResult tableQueryResult22 = getTableQueryResultModify(221,200,"Customer", "select * from Customer", Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult22,tableQueryResult22.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            TableQueryResult tableQueryResult23 = getTableQueryResultModify(222,200,"Customer", "select \\u002A from Customer", Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult23, tableQueryResult23.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            TableQueryResult tableQueryResult27 = getTableQueryResultModify(223,406,"Customer", "select * from Customer",Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult27, tableQueryResult27.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            TableQueryResult tableQueryResult28 = getTableQueryResultModify(224,200,"Customer", ";--select * from Customer", Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult28, tableQueryResult28.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            TableQueryResult tableQueryResult29 = getTableQueryResultModify(225,200,"Customer", ";select * from Customer",  Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult29, tableQueryResult29.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            TableQueryResult tableQueryResult20 = getTableQueryResultModify(226,200,"Customer", "if exists(select * from Customer)",  Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult20, tableQueryResult20.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);
            //endregion

            //region DELETE
            TableQueryResult tableQueryResult1 = getSingleQueryResult(330, 406, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult1, Integer.MAX_VALUE, "select", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            TableQueryResult tableQueryResult2 = getSingleQueryResult(331, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult2, Integer.MAX_VALUE, "select * from Customer", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            TableQueryResult tableQueryResult3 = getSingleQueryResult(332, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult3, 15, "select \\u002A from Customer", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            TableQueryResult tableQueryResult7 = getSingleQueryResult(336, 406, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult7, Integer.MIN_VALUE, "select * from Customer", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            TableQueryResult tableQueryResult8 = getSingleQueryResult(337, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult8, 3221, ";--select * from Customer", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            TableQueryResult tableQueryResult9 = getSingleQueryResult(338, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult9, 3321, ";select * from Customer", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            TableQueryResult tableQueryResult10 = getSingleQueryResult(339, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult10, 3421, "if exists(select * from Customer)", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);
            //endregion


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
