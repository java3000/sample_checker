package ru.vk.competition.minchecker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vk.competition.minchecker.dto.results.SingleQueryResult;
import ru.vk.competition.minchecker.utils.JsonBuilder;
import ru.vk.competition.minchecker.utils.RequestBuilder;
import ru.vk.competition.minchecker.utils.Urls;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartService {

    OkHttpClient client = new OkHttpClient();

    private final String RESULT_ID = "resultId";

    public void onStartMission() {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(1, 400);
            String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.ADD_NEW_QUERY_RESULT, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.ADD_SINGLE_QUERY).newBuilder()
                    .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission2() {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(2, 201);
            String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.ADD_NEW_QUERY_RESULT, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.ADD_SINGLE_QUERY).newBuilder()
                    .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                    .addQueryParameter("queryId", "1")
                    .addQueryParameter("query", "select * from Customer")
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission3() {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(3, 400);
            String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.ADD_NEW_QUERY_RESULT, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.ADD_SINGLE_QUERY).newBuilder()
                    .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                    .addQueryParameter("queryId", "\u7755")
                    .addQueryParameter("query", "select * from Customer")
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission4() {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(4, 406);
            String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.MODIFY_SINGLE_QUERY_RESULT, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.MODIFY_SINGLE_QUERY).newBuilder()
                    .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                    .addQueryParameter("queryId", String.valueOf(Integer.MAX_VALUE))
                    .addQueryParameter("query", "select * from Customer where order by Status")
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission6() {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(6, 406);
            String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.MODIFY_SINGLE_QUERY_RESULT, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.MODIFY_SINGLE_QUERY).newBuilder()
                    .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                    .addQueryParameter("queryId", "")
                    .addQueryParameter("query", "")
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission7() {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(7, 406);
            String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.MODIFY_SINGLE_QUERY_RESULT, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.MODIFY_SINGLE_QUERY).newBuilder()
                    .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                    .addQueryParameter("queryId", "null")
                    .addQueryParameter("query", "")
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartMission8() {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(8, 406);
            String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.MODIFY_SINGLE_QUERY_RESULT, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.MODIFY_SINGLE_QUERY).newBuilder()
                    .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                    .addQueryParameter("queryId", "2")
                    .addQueryParameter("query", "null")
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
