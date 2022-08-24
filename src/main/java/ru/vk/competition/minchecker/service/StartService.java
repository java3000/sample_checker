package ru.vk.competition.minchecker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.vk.competition.minchecker.dto.results.SingleQueryResult;
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
    private SingleQueryResult getQueryResult(int i, int i2, String uriType, String uriMethod) throws IOException {
        SingleQueryResult singleQueryResult = new SingleQueryResult(i, i2);
        String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
        RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
        Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + uriType + uriMethod, addSingleQueryResultRequestBody);
        System.out.println(addNewSingleQueryResult.url());
        Call call = client.newCall(addNewSingleQueryResult);
        Response response = call.execute();
        return singleQueryResult;
    }

    private void sendPostQueryRequest(SingleQueryResult singleQueryResult, String s, String s2, String uriType, String uriMethod) throws IOException {
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

    private void sendPutQueryRequest(SingleQueryResult singleQueryResult, String s, String s2, String uriType, String uriMethod) throws IOException {
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

    private void sendDeleteQueryRequest(SingleQueryResult singleQueryResult, String s, String s2, String uriType, String uriMethod) throws IOException {
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

    private void sendGetueryRequest(SingleQueryResult singleQueryResult, String s, String s2, String uriType, String uriMethod) throws IOException {
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
            SingleQueryResult singleQueryResult1 = getQueryResult(10, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult1, String.valueOf(Integer.MAX_VALUE), "select", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult2 = getQueryResult(11, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult2, String.valueOf(Integer.MAX_VALUE), "select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult3 = getQueryResult(12, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult3, "15", "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult4 = getQueryResult(13, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult4, "3q", "select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult5 = getQueryResult(14, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult5, "3", "update Customer set id in values(1,2,2)", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult6 = getQueryResult(15, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult6, "3", "select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult7 = getQueryResult(16, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult7, String.valueOf(Integer.MIN_VALUE), "select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult8 = getQueryResult(17, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult8, "3221", ";--select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult9 = getQueryResult(18, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult9, "3321", ";select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult10 = getQueryResult(19, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult10, "3421", "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission2() {
        try {
            SingleQueryResult singleQueryResult1 = getQueryResult(20, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult1, String.valueOf(Integer.MAX_VALUE), "select", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult2 = getQueryResult(21, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult2, String.valueOf(Integer.MAX_VALUE), "select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult3 = getQueryResult(22, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult3, "15", "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult4 = getQueryResult(23, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult4, "3q", "select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult5 = getQueryResult(24, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult5, "3", "update Customer set id in values(1,2,2)", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult6 = getQueryResult(25, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult6, "3", "select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult7 = getQueryResult(26, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult7, String.valueOf(Integer.MIN_VALUE), "select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult8 = getQueryResult(27, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult8, "3221", ";--select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult9 = getQueryResult(28, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult9, "3321", ";select * from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult10 = getQueryResult(29, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult10, "3421", "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission3() {
        try {
            SingleQueryResult singleQueryResult1 = getQueryResult(30, 406, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendDeleteQueryRequest(singleQueryResult1, String.valueOf(Integer.MAX_VALUE), "select", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult2 = getQueryResult(31, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendDeleteQueryRequest(singleQueryResult2, String.valueOf(Integer.MAX_VALUE), "select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult3 = getQueryResult(32, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendDeleteQueryRequest(singleQueryResult3, "15", "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult4 = getQueryResult(33, 406, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendDeleteQueryRequest(singleQueryResult4, "3q", "select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult5 = getQueryResult(34, 406, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendDeleteQueryRequest(singleQueryResult5, "3", "update Customer set id in values(1,2,2)", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult6 = getQueryResult(35, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendDeleteQueryRequest(singleQueryResult6, "3", "select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult7 = getQueryResult(36, 406, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendDeleteQueryRequest(singleQueryResult7, String.valueOf(Integer.MIN_VALUE), "select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult8 = getQueryResult(37, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendDeleteQueryRequest(singleQueryResult8, "3221", ";--select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult9 = getQueryResult(38, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendDeleteQueryRequest(singleQueryResult9, "3321", ";select * from Customer", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult10 = getQueryResult(39, 202, Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_RESULT);
            sendDeleteQueryRequest(singleQueryResult10, "3421", "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.DELETE_SINGLE_QUERY_BY_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission4() {
        try {
            SingleQueryResult singleQueryResult1 = getQueryResult(40, 406, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendGetueryRequest(singleQueryResult1, String.valueOf(Integer.MAX_VALUE), "select", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult2 = getQueryResult(41, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendGetueryRequest(singleQueryResult2, String.valueOf(Integer.MAX_VALUE), "select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult3 = getQueryResult(42, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendGetueryRequest(singleQueryResult3, "15", "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult4 = getQueryResult(43, 406, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendGetueryRequest(singleQueryResult4, "3q", "select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult5 = getQueryResult(44, 406, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendGetueryRequest(singleQueryResult5, "3", "update Customer set id in values(1,2,2)", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult6 = getQueryResult(45, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendGetueryRequest(singleQueryResult6, "3", "select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult7 = getQueryResult(46, 406, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendGetueryRequest(singleQueryResult7, String.valueOf(Integer.MIN_VALUE), "select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult8 = getQueryResult(47, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendGetueryRequest(singleQueryResult8, "3221", ";--select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult9 = getQueryResult(48, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendGetueryRequest(singleQueryResult9, "3321", ";select * from Customer", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);

            SingleQueryResult singleQueryResult10 = getQueryResult(49, 201, Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_RESULT);
            sendGetueryRequest(singleQueryResult10, "3421", "if exists(select * from Customer)", Urls.SINGLE_QUERY, Urls.EXECUTE_SINGLE_QUERY_BY_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void onStartMission5() {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(50, 500);
            String singleQueryResultJson = String.format("{\"resultId:\" %s, \"code\": %s, \"queryId\": %s, \"query\": %s}",
                    singleQueryResult.resultId, singleQueryResult.code, 3, "select * from Customer");
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + Urls.SINGLE_QUERY + "add-get-single-query-by-id-result", addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();

            HttpUrl url = HttpUrl.parse(API_ROOT + Urls.SINGLE_QUERY + "get-single-query-by-id/" + 3).newBuilder()
                    .addQueryParameter("resultId", String.valueOf(singleQueryResult.resultId))
                    .build();
            Request request = RequestBuilder.getRequestBuilder(url.toString());

            Call call1 = client.newCall(request);
            Response response1 = call1.execute();
            System.out.println(response1.code());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission6() {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(60, 200);
            String singleQueryResultJson = String.format("{\"resultId:\" %s, \"code\": %s}", singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(API_ROOT + Urls.SINGLE_QUERY + "get-all-single-queries", addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();

            HttpUrl url = HttpUrl.parse(API_ROOT + Urls.SINGLE_QUERY + "get-all-single-queries").newBuilder()
                    .addQueryParameter("resultId", String.valueOf(singleQueryResult.resultId))
                    .build();
            Request request = RequestBuilder.getRequestBuilder(url.toString());

            Call call1 = client.newCall(request);
            Response response1 = call1.execute();
            System.out.println(response1.code());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
