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

    private Result getResult(Query query, RequestType requestType, OperationType operationType, Integer resultId, Integer code, String uriType, String uriMethod) {
        Result result = null;
        String json = "";

        switch (requestType) {
            case SINGLE_QUERY:
                switch (operationType) {
                    case ADD:
                        result = new SingleQueryResult(resultId, code);
                        json = JsonBuilder.addSingleQueryResultJson(resultId, code);
                        break;
                    case GET:
                        result = new SingleQueryResult(resultId, code);
                        json = JsonBuilder.getSingleQueryResultJson(resultId, code, ((SingleQuery)query).getId(), ((SingleQuery) query).getQuery());
                        break;
                    case DELETE:
                        result = new SingleQueryResult(resultId, code);
                        json = JsonBuilder.deleteSingleQueryResultJson(resultId, code);
                        break;
                    case MODIFY:
                        result = new SingleQueryResult(resultId, code);
                        json = JsonBuilder.modifySingleQueryResultJson(resultId, code);
                        break;
                    case EXECUTE:
                    case GETALL:
                        result = new SingleQueryResult(resultId, code);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + operationType);
                }
                break;
            case TABLE_QUERY:
                switch (operationType) {
                    case ADD:
                        result = new TableQueryResult(resultId, code, (TableQuery) query);
                        json = JsonBuilder.addTableQueryResultJson(resultId, code, (TableQuery) query);
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

    private void sendPostRequest (Result result, Query query, RequestType requestType, OperationType operationType, String uriType, String uriMethod) {
        HttpUrl url = null;

        String bodyJson = "";

        switch (requestType) {
            case SINGLE_QUERY:
                url = HttpUrl.parse(API_ROOT + uriType + uriMethod).newBuilder()
                        .addQueryParameter("resultId", String.valueOf(((SingleQueryResult)result).getResultId()))
                        .build();

                switch (operationType) {
                    case ADD:
                        bodyJson = JsonBuilder.addSingleQueryResultJson(((SingleQueryResult) result).getResultId(), ((SingleQueryResult) result).getCode());
                        break;
                    case MODIFY:
                        bodyJson = JsonBuilder.modifySingleQueryJson(((SingleQuery)query).getId(), ((SingleQuery) query).getQuery());
                        break;
                    case GET:
                    case DELETE:
                    case EXECUTE:
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

            Query[] queries = new Query[] {
                    new SingleQuery(Integer.MAX_VALUE, "select"),
                    new SingleQuery(Integer.MAX_VALUE, "select * from Customer"),
                    new SingleQuery(15, "select \\u002A from Customer"),
                    new SingleQuery(Integer.MIN_VALUE, "select * from Customer"),
                    new SingleQuery(3221, ";--select * from Customer"),
                    new SingleQuery(3321, ";select * from Customer"),
                    new SingleQuery(3421, "if exists(select * from Customer)"),
            };

            //region --- ADD ---
            Result singleQueryResult1 = getResult(queries[0], RequestType.SINGLE_QUERY, OperationType.ADD, 10, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult1, queries[0], RequestType.SINGLE_QUERY, OperationType.ADD, Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult2 = getResult(queries[1], RequestType.SINGLE_QUERY, OperationType.ADD,11, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult2, queries[1], RequestType.SINGLE_QUERY, OperationType.ADD,Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult3 = getResult(queries[2], RequestType.SINGLE_QUERY, OperationType.ADD,12, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult3,  queries[2], RequestType.SINGLE_QUERY, OperationType.ADD, Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult7 = getResult(queries[3], RequestType.SINGLE_QUERY, OperationType.ADD,16, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult7, queries[3], RequestType.SINGLE_QUERY, OperationType.ADD,Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult8 = getResult(queries[4], RequestType.SINGLE_QUERY, OperationType.ADD,17, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult8, queries[4], RequestType.SINGLE_QUERY, OperationType.ADD, Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult9 = getResult(queries[5], RequestType.SINGLE_QUERY, OperationType.ADD,18, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult9, queries[5], RequestType.SINGLE_QUERY, OperationType.ADD, Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult10 = getResult(queries[6], RequestType.SINGLE_QUERY, OperationType.ADD,19, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostRequest(singleQueryResult10, queries[6], RequestType.SINGLE_QUERY, OperationType.ADD,Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);
            //endregion
/*
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
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
