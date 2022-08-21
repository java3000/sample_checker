package ru.vk.competition.minchecker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import retrofit2.Call;
import retrofit2.http.*;
import ru.vk.competition.minchecker.dto.results.SingleQuery;
import ru.vk.competition.minchecker.dto.results.SingleQueryResult;

@RequestMapping("/api/single-query")
public interface SingleQueryController {

    @GET("/add-new-query-result")
    public Call<Void> addResult(@Query("resultId") String resultId, @Query("code")String code);

    @POST("/add-new-query?resultId={id}")
    public Call<Void> addNewQuery (@Path("id") String id, @Body SingleQuery query);

    @POST("/add-modify-result")
    public Call<Void> addModifyResult (@Body SingleQueryResult singleQueryResult);

    @PUT("/modify-single-query?resultId={id}")
    public Call<Void> modifySingleQuery (@Path("id") String id, @Body SingleQuery query);

    @POST ("/add-delete-result")
    public Call<Void> addDeleteResult (@Body SingleQueryResult singleQueryResult);

    @DELETE("/delete-single-query-by-id/{id}?resultId={resid}")
    public Call<Void> deleteSingleQueryById (@Path("id") int id, @Path("resid") int resid);

    @POST("/add-execute-result")
    public Call<Void> addExecuteResult (@Body SingleQueryResult singleQueryResult);

    @GET("/execute-single-query-by-id/{id}?resultId={resid}")
    public Call<Void> executeSingleQueryById (@Path("id") int id, @Path("resid") int resid);

    @POST ("/add-get-single-query-by-id-result")
    public Call<Void> addGetSingleQueryByIdResult (@Body SingleQueryResult singleQueryResult);

    @GET ("/get-single-query-by-id/{id}?resultId={resid}")
    public Call<Void> getSingleQueryBiId (@Path("id") int id, @Path("resid") int resid);
}
