package ru.vk.competition.minchecker.base;

import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Component;
import ru.vk.competition.minchecker.utils.JsonBuilder;
import ru.vk.competition.minchecker.utils.RequestBuilder;
import ru.vk.competition.minchecker.utils.Urls;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class BaseTest {

    OkHttpClient client = new OkHttpClient();

    @PostConstruct
    private void sendBaseRequest() {
        try {
            /*
            Это правильный запрос, так как код = 400
             */
            String singleQueryResultJson = JsonBuilder.addSingleQueryResultJsonBuilder(1, 400);
            RequestBody addSingleQueryResultRequestBody = RequestBody.create(MediaType.parse("application/json"), singleQueryResultJson);
            Request addNewSingleQueryResult = RequestBuilder.postRequestBuilder(Urls.SINGLE_QUERY + Urls.ADD_NEW_QUERY_RESULT, addSingleQueryResultRequestBody);
            Call call = client.newCall(addNewSingleQueryResult);
            Response response = call.execute();
            System.out.println(response.code());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
