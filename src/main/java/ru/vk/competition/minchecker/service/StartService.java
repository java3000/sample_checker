package ru.vk.competition.minchecker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.vk.competition.minchecker.dto.results.SingleQueryResult;
import ru.vk.competition.minchecker.utils.JsonBuilder;
import ru.vk.competition.minchecker.utils.RequestBuilder;
import ru.vk.competition.minchecker.utils.Urls;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartService {

    OkHttpClient client = new OkHttpClient();

    private final String RESULT_ID = "resultId";

    @NotNull
    private SingleQueryResult getQueryResult(int i, int i2, String uriType, String uriMethod) throws IOException {
        SingleQueryResult singleQueryResult = new SingleQueryResult(i, i2);
        String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
        RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
        Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(uriType + uriMethod, addSingleQueryResultRequestBody);
        System.out.println(addNewSingleQueryResult.url());
        Call call = client.newCall(addNewSingleQueryResult);
        Response response = call.execute();
        System.out.println(response.code());
        return singleQueryResult;
    }

    private void sendPostQueryRequest(SingleQueryResult singleQueryResult, String s, String s2, String uriType, String uriMethod) throws IOException {
        HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + uriType + uriMethod).newBuilder()
                .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                .build();

        String bodyJson = JsonBuilder.modifySingleQueryResultJsonBuilder(s, s2);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyJson);
        Request request =  RequestBuilder.postRequestBuilder(url.toString(), body);

        Call call1 = client.newCall(request);
        Response response1 = call1.execute();
        System.out.println(response1.code());
    }

    private void sendPutQueryRequest(SingleQueryResult singleQueryResult, String s, String s2, String uriType, String uriMethod) throws IOException {
        HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + uriType + uriMethod).newBuilder()
                .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                .build();

        String bodyJson = JsonBuilder.modifySingleQueryResultJsonBuilder(s, s2);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyJson);
        Request request =  RequestBuilder.putRequestBuilder(url.toString(), body);

        Call call1 = client.newCall(request);
        Response response1 = call1.execute();
        System.out.println(response1.code());
    }

    private void sendDeleteQueryRequest(SingleQueryResult singleQueryResult, String s, String s2, String uriType, String uriMethod) throws IOException {
        HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + uriType + uriMethod).newBuilder()
                .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                .build();

        String bodyJson = JsonBuilder.modifySingleQueryResultJsonBuilder(s, s2);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyJson);
        Request request =  RequestBuilder.deleteRequestBuilder(url.toString(), body);

        Call call1 = client.newCall(request);
        Response response1 = call1.execute();
        System.out.println(response1.code());
    }

    public void onStartMission() {
        try {
            // --ADD---
            SingleQueryResult singleQueryResult1 = getQueryResult(1, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult1, "", "", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult2 = getQueryResult(2, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult2, "15", "select \\u002A from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult3 = getQueryResult(3, 400, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult3, "3q", "select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult4 = getQueryResult(4, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult4, "3", "select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            SingleQueryResult singleQueryResult5 = getQueryResult(5, 201, Urls.SINGLE_QUERY, Urls.ADD_NEW_QUERY_RESULT);
            sendPostQueryRequest(singleQueryResult5, "3", "select * from Customer", Urls.SINGLE_QUERY, Urls.ADD_SINGLE_QUERY);

            // ---DELETE---
            // ---EXECUTE---
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission2() {
        try {
            // ---MODIFY---
            SingleQueryResult singleQueryResult6 = getQueryResult(6, 200, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult6, "3", "select * from Artist", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult7 = getQueryResult(7, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult7, "333", "select * from Artists", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult8 = getQueryResult(8, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult8, "", "", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult9 = getQueryResult(9, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult9, "qwe", "select COUNT(*) from Customer", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            SingleQueryResult singleQueryResult10 = getQueryResult(10, 406, Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY_RESULT);
            sendPutQueryRequest(singleQueryResult10, "1", "qwe", Urls.SINGLE_QUERY, Urls.MODIFY_SINGLE_QUERY);

            // ---DELETE---
            // ---EXECUTE---
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
