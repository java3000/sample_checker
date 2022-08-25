package ru.vk.competition.minchecker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;
import ru.vk.competition.minchecker.dto.results.*;
import ru.vk.competition.minchecker.utils.*;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartService {

    OkHttpClient client = new OkHttpClient();
    public static final String API_ROOT = new SystemVariables().apiRoot();

    private Result getResult(Query query, RequestType requestType, Integer resultId, Integer code, String uriType, String uriMethod) {
        Result result = null;
        String json = "";

        switch (requestType) {
            case SINGLE_QUERY:
                result = new SingleQueryResult(resultId, code);
                json = JsonBuilder.addSingleQueryResultJsonBuilder(resultId, code);
                break;
            case TABLE_QUERY:
                result = new TableQueryResult(resultId, code, (TableQuery) query);
                json = JsonBuilder.addTableQueryResultJsonBuilder(resultId, code, (TableQuery) query);
                break;
            case TABLE:
                break;
            case REPORT:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestType);
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Request addNewResult = RequestBuilder.postRequestBuilder(API_ROOT + uriType + uriMethod, body);
        System.out.println(addNewResult.url());
        Call call = client.newCall(addNewResult);

        try {
            Response response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void sendPostRequest (Result result, RequestType requestType, OperationType operationType, String uriType, String uriMethod) {
        HttpUrl url = null;

        String bodyJson = "";

        switch (requestType) {
            case SINGLE_QUERY:
                url = HttpUrl.parse(API_ROOT + uriType + uriMethod).newBuilder()
                        .addQueryParameter("resultId", String.valueOf(((SingleQueryResult)result).getResultId()))
                        .build();

                switch (operationType) {
                    case ADD:
                        break;
                    case GET:
                        break;
                    case DELETE:
                        break;
                    case MODIFY:
                        break;
                    case EXECUTE:
                        break;
                    case GETALL:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + operationType);
                }
                break;
            case TABLE_QUERY:
                url = HttpUrl.parse(API_ROOT + uriType + uriMethod).newBuilder()
                        .addQueryParameter("resultId", String.valueOf(((TableQueryResult)result).getResultId()))
                        .build();

                switch (operationType) {
                    case ADD:
                        break;
                    case GET:
                        break;
                    case DELETE:
                        break;
                    case MODIFY:
                        break;
                    case EXECUTE:
                        break;
                    case GETALL:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + operationType);
                }
                break;
            case TABLE:
                break;
            case REPORT:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestType);
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyJson);
        Request request = RequestBuilder.postRequestBuilder(url.toString(), body);

        Call call1 = client.newCall(request);

        Response response = null;
        try {
            response = call1.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response.code());
    }

    public void onStartMission() {
        try {
            //region --- ADD ---
            Query singleQueryResult1 = getResult(10, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult1, Integer.MAX_VALUE, "select", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Query singleQueryResult2 = getResult(11, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult2, Integer.MAX_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Query singleQueryResult3 = getResult(12, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult3, 15, "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Query singleQueryResult7 = getResult(16, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult7, Integer.MIN_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Query singleQueryResult8 = getResult(17, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult8, 3221, ";--select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Query singleQueryResult9 = getResult(18, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult9, 3321, ";select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Query singleQueryResult10 = getResult(19, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult10, 3421, "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);
            //endregion

            //region --- MODIFY ---
            Query singleQueryResult21 = getResult(20, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult21, Integer.MAX_VALUE, "select", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Query singleQueryResult22 = getResult(21, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult22, Integer.MAX_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Query singleQueryResult23 = getResult(22, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult23, 15, "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Query singleQueryResult27 = getResult(26, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult27, Integer.MIN_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Query singleQueryResult28 = getResult(27, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult28, 3221, ";--select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Query singleQueryResult29 = getResult(28, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult29, 3321, ";select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Query singleQueryResult20 = getResult(29, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
                sendPutSingleQueryRequest(singleQueryResult20, 3421, "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);
            //endregion

            //region --- EXECUTE ---
            Query singleQueryResult1 = getResult(40, 406, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult1, Integer.MAX_VALUE, "select", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult2 = getResult(41, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult2, Integer.MAX_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult3 = getResult(42, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult3, 15, "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult7 = getResult(46, 406, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult7, Integer.MIN_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult8 = getResult(47, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult8, 3221, ";--select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult9 = getResult(48, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult9, 3321, ";select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult10 = getResult(49, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
                sendGetSingleQueryRequest(singleQueryResult10, 3421, "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);
            //endregion

            //region --- DELETE ---
            Query singleQueryResult1 = getResult(30, 406, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult1, Integer.MAX_VALUE, "select", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult2 = getResult(31, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult2, Integer.MAX_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult3 = getResult(32, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult3, 15, "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult7 = getResult(36, 406, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult7, Integer.MIN_VALUE, "select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult8 = getResult(37, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult8, 3221, ";--select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult9 = getResult(38, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult9, 3321, ";select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Query singleQueryResult10 = getResult(39, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
                sendDeleteSingleQueryRequest(singleQueryResult10, 3421, "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);
            //endregion

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
            Query tableQueryResult1 = getResult(110, "Customer", "select", 406, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostRequest(tableQueryResult1, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Query tableQueryResult2 = getResult(111, "Customer", "select * from Customer", 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostRequest(tableQueryResult2, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Query tableQueryResult3 = getResult(112, "Customer", "select \\u002A from Customer", 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostRequest(tableQueryResult3, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Query tableQueryResult4 = getResult(113, "Customer", "select * from Customer", 406, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostRequest(tableQueryResult4, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Query tableQueryResult5 = getResult(114, "Customer", ";--select * from Customer", 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostRequest(tableQueryResult5, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Query tableQueryResult6 = getResult(115, "Customer", ";select * from Customer", 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostRequest(tableQueryResult6, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Query tableQueryResult7 = getResult(116, "Customer", "if exists(select * from Customer)", 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendPostRequest(tableQueryResult7, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);
            //endregion

            //region MODIFY
            Query tableQueryResult21 = getResult(220, 406, "Customer", "select", Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult21, tableQueryResult21.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Query tableQueryResult22 = getResult(221, 200, "Customer", "select * from Customer", Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult22, tableQueryResult22.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Query tableQueryResult23 = getResult(222, 200, "Customer", "select \\u002A from Customer", Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult23, tableQueryResult23.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Query tableQueryResult27 = getResult(223, 406, "Customer", "select * from Customer", Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult27, tableQueryResult27.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Query tableQueryResult28 = getResult(224, 200, "Customer", ";--select * from Customer", Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult28, tableQueryResult28.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Query tableQueryResult29 = getResult(225, 200, "Customer", ";select * from Customer", Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult29, tableQueryResult29.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Query tableQueryResult20 = getResult(226, 200, "Customer", "if exists(select * from Customer)", Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendPutTableQueryRequest(tableQueryResult20, tableQueryResult20.tableQuery, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);
            //endregion

            //region DELETE
            Query tableQueryResult1 = getResult(330, 406, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult1, Integer.MAX_VALUE, "select", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Query tableQueryResult2 = getResult(331, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult2, Integer.MAX_VALUE, "select * from Customer", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Query tableQueryResult3 = getResult(332, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult3, 15, "select \\u002A from Customer", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Query tableQueryResult7 = getResult(336, 406, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult7, Integer.MIN_VALUE, "select * from Customer", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Query tableQueryResult8 = getResult(337, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult8, 3221, ";--select * from Customer", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Query tableQueryResult9 = getResult(338, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult9, 3321, ";select * from Customer", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Query tableQueryResult10 = getResult(339, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendDeleteSingleQueryRequest(tableQueryResult10, 3421, "if exists(select * from Customer)", Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);
            //endregion


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
