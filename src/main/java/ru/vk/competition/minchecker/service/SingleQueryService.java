package ru.vk.competition.minchecker.service;

import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import ru.vk.competition.minchecker.dto.results.SingleQueryResult;
import ru.vk.competition.minchecker.utils.JsonBuilder;
import ru.vk.competition.minchecker.utils.RequestBuilder;
import ru.vk.competition.minchecker.utils.Urls;

@Service
@RequiredArgsConstructor
public class SingleQueryService {

    //private final SingleQueryRepository queryRepository;
    OkHttpClient client = new OkHttpClient();
    private final String RESULT_ID = "resultId";

    public void addResult(String resultId, String code) {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(1, 400);
            String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.ADD_NEW_QUERY_RESULT, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            okhttp3.Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.ADD_SINGLE_QUERY).newBuilder()
                    .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okhttp3.Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean check(String resultId, String code) {
        if(resultId == null || Integer.parseInt(resultId) <= 0) {
            return true;
        }
        if(code == null || Integer.parseInt(code) <= 0) {
            return true;
        }
        return false;
    }

    public void addNewQuery(String resultId, String queryId, String query) {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(1, 400);
            String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.ADD_SINGLE_QUERY, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            okhttp3.Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url = HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.ADD_SINGLE_QUERY).newBuilder()
                    .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okhttp3.Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addModifyResult(Integer resultId, Integer code) {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(2, 406);
            String singleQueryResultJson =
                    JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody =
                    RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult =
                    RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.MODIFY_SINGLE_QUERY, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            okhttp3.Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url =
                    HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.MODIFY_SINGLE_QUERY).newBuilder()
                    .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okhttp3.Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifySingleQuery(String resultId, String queryId, String query) {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(2, 406);
            String singleQueryResultJson =
                    JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody =
                    RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult =
                    RequestBuilder.putRequestBuilder(Urls.SINGLE_QUERY + "add-modify-result", addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            okhttp3.Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url =
                    HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.MODIFY_SINGLE_QUERY).newBuilder()
                            .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                            .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okhttp3.Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDeleteResult(String resultId, String code) {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(2, 406);
            String singleQueryResultJson =
                    JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody =
                    RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult =
                    RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.DELETE_SINGLE_QUERY_BY_ID, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            okhttp3.Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url =
                    HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.DELETE_SINGLE_QUERY_BY_ID).newBuilder()
                            .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                            .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okhttp3.Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<Void> deleteSingleQueryById(int id, int resid) {
            try {
                /*if (queryRepository.findByQueryId(String.valueOf(id)).map(SingleQuery::getQueryId).isEmpty()) {
                    return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
                }*/
                //TODO ● Не существует заданного resultId

                //TODO add some work here
                return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
            }
    }

    public void addExecuteResult(String resultId, String code) {
        try {
            SingleQueryResult singleQueryResult = new SingleQueryResult(2, 406);
            String singleQueryResultJson =
                    JsonBuilder.addSingleQueryResultJsonBuilder(singleQueryResult.resultId, singleQueryResult.code);
            RequestBody addSingleQueryResultRequestBody =
                    RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult =
                    RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.EXECUTE_SINGLE_QUERY_BY_ID, addSingleQueryResultRequestBody);
            System.out.println(addNewSingleQueryResult.url());
            okhttp3.Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());

            HttpUrl url =
                    HttpUrl.parse(RequestBuilder.API_ROOT + Urls.SINGLE_QUERY + Urls.EXECUTE_SINGLE_QUERY_BY_ID).newBuilder()
                            .addQueryParameter(RESULT_ID, singleQueryResult.resultId.toString())
                            .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okhttp3.Call call1 = client.newCall(request);
            call1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<Void> executeSingleQueryById(int id, int resid) {
            try {
                /*if (queryRepository.findByQueryId(String.valueOf(id)).map(SingleQuery::getQueryId).isEmpty()) {
                    return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
                }*/
                //TODO ● Синтаксис запроса неверный
                //TODO ● Не существует заданного resultId

                //TODO add some work here
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
            }
    }

    public ResponseEntity<Void> addGetSingleQueryByIdResult(String resultId, String code) {
            try {
                if (check(resultId, code)) return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                if(Integer.parseInt(code) != HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                    return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                }

                //TODO add some work here
                return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
    }

    public ResponseEntity<Void> getSingleQueryBiId(int id, int resid) {
            try {
                /*if (queryRepository.findByQueryId(String.valueOf(id)).map(SingleQuery::getQueryId).isEmpty()) {
                    return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
                }*/
                //TODO ● Не существует заданного resultId

                //TODO add some work here
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
