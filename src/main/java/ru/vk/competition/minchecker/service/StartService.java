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

    private Result getResult(Query query, QueryType queryType, OperationType operationType, Integer resultId, Integer code, String uriType, String uriMethod) {
        Result result = null;
        String json = "";

        switch (queryType) {
            case SINGLE_QUERY:
                switch (operationType) {
                    case ADD:
                        result = new SingleQueryResult(resultId, code);
                        json = JsonBuilder.addSingleQueryResultJson(resultId, code);
                        break;
                    case GET:
                        result = new SingleQueryResult(resultId, code);
                        json = JsonBuilder.getSingleQueryResultJson(resultId, code, ((SingleQuery) query).getId(), ((SingleQuery) query).getQuery());
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
                throw new IllegalStateException("Unexpected value: " + queryType);
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

    private void sendRequest(Result result, Query query, QueryType queryType, OperationType operationType, RequestType requestType, String uriType, String uriMethod) {
        HttpUrl url = null;
        Request request = null;
        String bodyJson = "";

        switch (queryType) {
            case SINGLE_QUERY:
                url = HttpUrl.parse(API_ROOT + uriType + uriMethod).newBuilder()
                        .addQueryParameter("resultId", String.valueOf(((SingleQueryResult) result).getResultId()))
                        .build();

                switch (operationType) {
                    case ADD:
                        bodyJson = JsonBuilder.addSingleQueryResultJson(((SingleQueryResult) result).getResultId(), ((SingleQueryResult) result).getCode());
                        break;
                    case MODIFY:
                        bodyJson = JsonBuilder.modifySingleQueryJson(((SingleQuery) query).getId(), ((SingleQuery) query).getQuery());
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
                        .addQueryParameter("resultId", String.valueOf(((TableQueryResult) result).getResultId()))
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
                throw new IllegalStateException("Unexpected value: " + queryType);
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyJson);

        switch (requestType) {
            case POST:
                request = RequestBuilder.postRequestBuilder(url.toString(), body);
                break;
            case PUT:
                request = RequestBuilder.putRequestBuilder(url.toString(), body);
                break;
            case DELETE:
                request = RequestBuilder.deleteRequestBuilder(url.toString() + "/" + ((SingleQuery)query).getId(), body);
                break;
            case GET:
                request = RequestBuilder.getRequestBuilder(url.toString() + "/" + ((SingleQuery)query).getId());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestType);
        }

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

            Query[] queries = new Query[]{
                    new SingleQuery(Integer.MAX_VALUE, "select"),
                    new SingleQuery(Integer.MAX_VALUE, "select * from Customer"),
                    new SingleQuery(15, "select \\u002A from Customer"),
                    new SingleQuery(Integer.MIN_VALUE, "select * from Customer"),
                    new SingleQuery(3221, ";--select * from Customer"),
                    new SingleQuery(3321, ";select * from Customer"),
                    new SingleQuery(3421, "if exists(select * from Customer)"),
            };

            //region --- ADD ---
            Result singleQueryResult1 = getResult(queries[0], QueryType.SINGLE_QUERY, OperationType.ADD, 10, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendRequest(singleQueryResult1, queries[0], QueryType.SINGLE_QUERY, OperationType.ADD, RequestType.POST, Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult2 = getResult(queries[1], QueryType.SINGLE_QUERY, OperationType.ADD, 11, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendRequest(singleQueryResult2, queries[1], QueryType.SINGLE_QUERY, OperationType.ADD, RequestType.POST, Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult3 = getResult(queries[2], QueryType.SINGLE_QUERY, OperationType.ADD, 12, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendRequest(singleQueryResult3, queries[2], QueryType.SINGLE_QUERY, OperationType.ADD, RequestType.POST, Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult7 = getResult(queries[3], QueryType.SINGLE_QUERY, OperationType.ADD, 16, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendRequest(singleQueryResult7, queries[3], QueryType.SINGLE_QUERY, OperationType.ADD, RequestType.POST, Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult8 = getResult(queries[4], QueryType.SINGLE_QUERY, OperationType.ADD, 17, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendRequest(singleQueryResult8, queries[4], QueryType.SINGLE_QUERY, OperationType.ADD, RequestType.POST, Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult9 = getResult(queries[5], QueryType.SINGLE_QUERY, OperationType.ADD, 18, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendRequest(singleQueryResult9, queries[5], QueryType.SINGLE_QUERY, OperationType.ADD, RequestType.POST, Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            Result singleQueryResult10 = getResult(queries[6], QueryType.SINGLE_QUERY, OperationType.ADD, 19, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendRequest(singleQueryResult10, queries[6], QueryType.SINGLE_QUERY, OperationType.ADD, RequestType.POST, Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);
            //endregion

            //region --- MODIFY ---
            Result singleQueryResult21 = getResult(queries[0], QueryType.SINGLE_QUERY, OperationType.MODIFY, 20, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult21, queries[0], QueryType.SINGLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Result singleQueryResult22 = getResult(queries[1], QueryType.SINGLE_QUERY, OperationType.MODIFY, 21, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult22, queries[1], QueryType.SINGLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Result singleQueryResult23 = getResult(queries[2], QueryType.SINGLE_QUERY, OperationType.MODIFY, 22, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult23, queries[2], QueryType.SINGLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Result singleQueryResult27 = getResult(queries[3], QueryType.SINGLE_QUERY, OperationType.MODIFY, 26, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult27, queries[3], QueryType.SINGLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Result singleQueryResult28 = getResult(queries[4], QueryType.SINGLE_QUERY, OperationType.MODIFY, 27, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult28, queries[4], QueryType.SINGLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Result singleQueryResult29 = getResult(queries[5], QueryType.SINGLE_QUERY, OperationType.MODIFY, 28, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult29, queries[5], QueryType.SINGLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            Result singleQueryResult20 = getResult(queries[6], QueryType.SINGLE_QUERY, OperationType.MODIFY, 29, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult20, queries[6], QueryType.SINGLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);
            //endregion

            //region --- EXECUTE ---
            Result singleQueryResult31 = getResult(queries[0], QueryType.SINGLE_QUERY, OperationType.EXECUTE, 40, 406, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult31, queries[0], QueryType.SINGLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult32 = getResult(queries[1], QueryType.SINGLE_QUERY, OperationType.EXECUTE, 41, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult32, queries[1], QueryType.SINGLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult33 = getResult(queries[2], QueryType.SINGLE_QUERY, OperationType.EXECUTE, 42, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult33, queries[2], QueryType.SINGLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult34 = getResult(queries[3], QueryType.SINGLE_QUERY, OperationType.EXECUTE, 46, 406, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult34, queries[3], QueryType.SINGLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult35 = getResult(queries[4], QueryType.SINGLE_QUERY, OperationType.EXECUTE, 47, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult35, queries[4], QueryType.SINGLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult36 = getResult(queries[5], QueryType.SINGLE_QUERY, OperationType.EXECUTE, 48, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult36, queries[5], QueryType.SINGLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult37 = getResult(queries[6], QueryType.SINGLE_QUERY, OperationType.EXECUTE, 49, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult37, queries[6], QueryType.SINGLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);
            //endregion

            //region --- DELETE ---
            Result singleQueryResult41 = getResult(queries[0], QueryType.SINGLE_QUERY, OperationType.DELETE, 30, 406, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult41, queries[0], QueryType.SINGLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult42 = getResult(queries[1], QueryType.SINGLE_QUERY, OperationType.DELETE,31, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult42, queries[1], QueryType.SINGLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult43 = getResult(queries[2], QueryType.SINGLE_QUERY, OperationType.DELETE,32, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult43, queries[2], QueryType.SINGLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult44 = getResult(queries[3], QueryType.SINGLE_QUERY, OperationType.DELETE,36, 406, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult44, queries[3], QueryType.SINGLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult45 = getResult(queries[4], QueryType.SINGLE_QUERY, OperationType.DELETE,37, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult45,  queries[4], QueryType.SINGLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult46 = getResult(queries[5], QueryType.SINGLE_QUERY, OperationType.DELETE,38, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult46, queries[5], QueryType.SINGLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            Result singleQueryResult47 = getResult(queries[6], QueryType.SINGLE_QUERY, OperationType.DELETE,39, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendRequest(singleQueryResult47,  queries[6], QueryType.SINGLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);
            //endregion

            // --- GET ---
            try {
                SingleQueryResult singleQueryResult = new SingleQueryResult(50, 500);
                String singleQueryResultJson = String.format("{\"resultId:\"%1$d, \"code\":%1$d, \"queryId\":%1$d, \"query\":%s}",
                        singleQueryResult.getResultId(), singleQueryResult.getCode(), 3, "select * from Customer");
                RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
                Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + Urls.SINGLE_QUERY + Urls.GET_SINGLE_QUERY_BY_ID_RESULT, addSingleQueryResultRequestBody);
                System.out.println(addNewSingleQueryResult.url());
                Call call = client.newCall(addNewSingleQueryResult);
                Response response = call.execute();

                HttpUrl url = HttpUrl.parse(API_ROOT + Urls.SINGLE_QUERY + Urls.GET_SINGLE_QUERY_BY_ID + "/" + 3).newBuilder()
                        .addQueryParameter("resultId", String.valueOf(singleQueryResult.getResultId()))
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
                String singleQueryResultJson = String.format("{\"resultId:\"%1$d, \"code\":%1$d}", singleQueryResult.getResultId(), singleQueryResult.getCode());
                RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
                Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + Urls.SINGLE_QUERY + Urls.GET_ALL_SINGLE_QUERIES, addSingleQueryResultRequestBody);
                System.out.println(addNewSingleQueryResult.url());
                Call call = client.newCall(addNewSingleQueryResult);
                Response response = call.execute();

                HttpUrl url = HttpUrl.parse(API_ROOT + Urls.SINGLE_QUERY + Urls.GET_ALL_SINGLE_QUERIES).newBuilder()
                        .addQueryParameter("resultId", String.valueOf(singleQueryResult.getResultId()))
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

            Query[] queries = new Query[]{
                    new SingleQuery(Integer.MAX_VALUE, "select"),
                    new SingleQuery(Integer.MAX_VALUE, "select * from Customer"),
                    new SingleQuery(15, "select \\u002A from Customer"),
                    new SingleQuery(Integer.MIN_VALUE, "select * from Customer"),
                    new SingleQuery(3221, ";--select * from Customer"),
                    new SingleQuery(3321, ";select * from Customer"),
                    new SingleQuery(3421, "if exists(select * from Customer)"),
            };

            //region --- ADD ---
            Result singleQueryResult1 = getResult(queries[0], QueryType.TABLE_QUERY, OperationType.ADD, 10, 406, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult1, queries[0], QueryType.TABLE_QUERY, OperationType.ADD, RequestType.POST, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Result singleQueryResult2 = getResult(queries[1], QueryType.TABLE_QUERY, OperationType.ADD, 11, 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult2, queries[1], QueryType.TABLE_QUERY, OperationType.ADD, RequestType.POST, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Result singleQueryResult3 = getResult(queries[2], QueryType.TABLE_QUERY, OperationType.ADD, 12, 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult3, queries[2], QueryType.TABLE_QUERY, OperationType.ADD, RequestType.POST, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Result singleQueryResult7 = getResult(queries[3], QueryType.TABLE_QUERY, OperationType.ADD, 16, 406, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult7, queries[3], QueryType.TABLE_QUERY, OperationType.ADD, RequestType.POST, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Result singleQueryResult8 = getResult(queries[4], QueryType.TABLE_QUERY, OperationType.ADD, 17, 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult8, queries[4], QueryType.TABLE_QUERY, OperationType.ADD, RequestType.POST, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Result singleQueryResult9 = getResult(queries[5], QueryType.TABLE_QUERY, OperationType.ADD, 18, 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult9, queries[5], QueryType.TABLE_QUERY, OperationType.ADD, RequestType.POST, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);

            Result singleQueryResult10 = getResult(queries[6], QueryType.TABLE_QUERY, OperationType.ADD, 19, 201, Urls.TABLE_QUERY, Urls.ADD_NEW_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult10, queries[6], QueryType.TABLE_QUERY, OperationType.ADD, RequestType.POST, Urls.TABLE_QUERY, Urls.ADD_TABLE_QUERY);
            //endregion

            //region --- MODIFY ---
            Result singleQueryResult21 = getResult(queries[0], QueryType.TABLE_QUERY, OperationType.MODIFY, 20, 406, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult21, queries[0], QueryType.TABLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Result singleQueryResult22 = getResult(queries[1], QueryType.TABLE_QUERY, OperationType.MODIFY, 21, 200, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult22, queries[1], QueryType.TABLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Result singleQueryResult23 = getResult(queries[2], QueryType.TABLE_QUERY, OperationType.MODIFY, 22, 200, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult23, queries[2], QueryType.TABLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Result singleQueryResult27 = getResult(queries[3], QueryType.TABLE_QUERY, OperationType.MODIFY, 26, 406, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult27, queries[3], QueryType.TABLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Result singleQueryResult28 = getResult(queries[4], QueryType.TABLE_QUERY, OperationType.MODIFY, 27, 200, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult28, queries[4], QueryType.TABLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Result singleQueryResult29 = getResult(queries[5], QueryType.TABLE_QUERY, OperationType.MODIFY, 28, 200, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult29, queries[5], QueryType.TABLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);

            Result singleQueryResult20 = getResult(queries[6], QueryType.TABLE_QUERY, OperationType.MODIFY, 29, 200, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult20, queries[6], QueryType.TABLE_QUERY, OperationType.MODIFY, RequestType.PUT, Urls.TABLE_QUERY, Urls.MODIFY_TABLE_QUERY);
            //endregion

            //region --- EXECUTE ---
            Result singleQueryResult31 = getResult(queries[0], QueryType.TABLE_QUERY, OperationType.EXECUTE, 40, 406, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult31, queries[0], QueryType.TABLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_BY_ID);

            Result singleQueryResult32 = getResult(queries[1], QueryType.TABLE_QUERY, OperationType.EXECUTE, 41, 201, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult32, queries[1], QueryType.TABLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_BY_ID);

            Result singleQueryResult33 = getResult(queries[2], QueryType.TABLE_QUERY, OperationType.EXECUTE, 42, 201, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult33, queries[2], QueryType.TABLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_BY_ID);

            Result singleQueryResult34 = getResult(queries[3], QueryType.TABLE_QUERY, OperationType.EXECUTE, 46, 406, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult34, queries[3], QueryType.TABLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_BY_ID);

            Result singleQueryResult35 = getResult(queries[4], QueryType.TABLE_QUERY, OperationType.EXECUTE, 47, 201, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult35, queries[4], QueryType.TABLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_BY_ID);

            Result singleQueryResult36 = getResult(queries[5], QueryType.TABLE_QUERY, OperationType.EXECUTE, 48, 201, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult36, queries[5], QueryType.TABLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_BY_ID);

            Result singleQueryResult37 = getResult(queries[6], QueryType.TABLE_QUERY, OperationType.EXECUTE, 49, 201, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult37, queries[6], QueryType.TABLE_QUERY, OperationType.EXECUTE, RequestType.GET, Urls.TABLE_QUERY, Urls.EXECUTE_TABLE_QUERY_BY_ID);
            //endregion

            //region --- DELETE ---
            Result singleQueryResult41 = getResult(queries[0], QueryType.TABLE_QUERY, OperationType.DELETE, 30, 406, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult41, queries[0], QueryType.TABLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Result singleQueryResult42 = getResult(queries[1], QueryType.TABLE_QUERY, OperationType.DELETE,31, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult42, queries[1], QueryType.TABLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Result singleQueryResult43 = getResult(queries[2], QueryType.TABLE_QUERY, OperationType.DELETE,32, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult43, queries[2], QueryType.TABLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Result singleQueryResult44 = getResult(queries[3], QueryType.TABLE_QUERY, OperationType.DELETE,36, 406, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult44, queries[3], QueryType.TABLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Result singleQueryResult45 = getResult(queries[4], QueryType.TABLE_QUERY, OperationType.DELETE,37, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult45,  queries[4], QueryType.TABLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Result singleQueryResult46 = getResult(queries[5], QueryType.TABLE_QUERY, OperationType.DELETE,38, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult46, queries[5], QueryType.TABLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);

            Result singleQueryResult47 = getResult(queries[6], QueryType.TABLE_QUERY, OperationType.DELETE,39, 202, Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_RESULT);
            sendRequest(singleQueryResult47,  queries[6], QueryType.TABLE_QUERY, OperationType.DELETE, RequestType.DELETE,  Urls.TABLE_QUERY, Urls.DELETE_TABLE_QUERY_BY_ID);
            //endregion

            // --- GET ---
            try {
                SingleQueryResult singleQueryResult = new SingleQueryResult(50, 500);
                String singleQueryResultJson = String.format("{\"resultId:\"%1$d, \"code\":%1$d, \"queryId\":%1$d, \"query\":%s}",
                        singleQueryResult.getResultId(), singleQueryResult.getCode(), 3, "select * from Customer");
                RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
                Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + Urls.TABLE_QUERY + Urls.GET_TABLE_QUERY_BY_ID_RESULT, addSingleQueryResultRequestBody);
                System.out.println(addNewSingleQueryResult.url());
                Call call = client.newCall(addNewSingleQueryResult);
                Response response = call.execute();

                HttpUrl url = HttpUrl.parse(API_ROOT + Urls.TABLE_QUERY + Urls.GET_TABLE_QUERY_BY_ID + "/" + 3).newBuilder()
                        .addQueryParameter("resultId", String.valueOf(singleQueryResult.getResultId()))
                        .build();
                Request request = RequestBuilder.getRequestBuilder(url.toString());

                Call call1 = client.newCall(request);
                Response response1 = call1.execute();
                System.out.println(response1.code());

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                SingleQueryResult singleQueryResult = new SingleQueryResult(50, 500);
                String singleQueryResultJson = String.format("{\"resultId:\"%1$d, \"code\":%1$d, \"queryId\":%1$d, \"query\":%s}",
                        singleQueryResult.getResultId(), singleQueryResult.getCode(), 3, "select * from Customer");
                RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
                Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + Urls.TABLE_QUERY + Urls.GET_TABLE_QUERY_BY_ID_RESULT, addSingleQueryResultRequestBody);
                System.out.println(addNewSingleQueryResult.url());
                Call call = client.newCall(addNewSingleQueryResult);
                Response response = call.execute();

                HttpUrl url = HttpUrl.parse(API_ROOT + Urls.TABLE_QUERY + Urls.GET_ALL_TABLE_QUERY_BY_NAME + "/" + "Customer").newBuilder()
                        .addQueryParameter("resultId", String.valueOf(singleQueryResult.getResultId()))
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
                String singleQueryResultJson = String.format("{\"resultId:\"%1$d, \"code\":%1$d}", singleQueryResult.getResultId(), singleQueryResult.getCode());
                RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
                Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + Urls.TABLE_QUERY + Urls.GET_ALL_TABLE_QUERIES, addSingleQueryResultRequestBody);
                System.out.println(addNewSingleQueryResult.url());
                Call call = client.newCall(addNewSingleQueryResult);
                Response response = call.execute();

                HttpUrl url = HttpUrl.parse(API_ROOT + Urls.TABLE_QUERY + Urls.GET_ALL_TABLE_QUERIES).newBuilder()
                        .addQueryParameter("resultId", String.valueOf(singleQueryResult.getResultId()))
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
}
