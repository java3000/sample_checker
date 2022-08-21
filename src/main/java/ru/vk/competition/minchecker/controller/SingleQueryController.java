package ru.vk.competition.minchecker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.*;
import ru.vk.competition.minchecker.dto.results.SingleQuery;
import ru.vk.competition.minchecker.dto.results.SingleQueryResult;

@RestController
@RequestMapping("/api/single-query")
public interface SingleQueryController {

    @GET("/add-new-query-result")
    public ResponseEntity<Void> addResult(@Query("resultId") String resultId, @Query("code")String code);

    @POST("/add-new-query?resultId={id}")
    public ResponseEntity<Void> addNewQuery (@Path("id") String id, @Body SingleQuery query);

    @POST("/add-modify-result")
    public ResponseEntity<Void> addModifyResult (@Body SingleQueryResult singleQueryResult);

    @PUT("/modify-single-query?resultId={id}")
    public ResponseEntity<Void> modifySingleQuery (@Path("id") String id, @Body SingleQuery query);

    @POST ("/add-delete-result")
    public ResponseEntity<Void> addDeleteResult (@Body SingleQueryResult singleQueryResult);

    @DELETE("/delete-single-query-by-id/{id}?resultId={resid}")
    public ResponseEntity<Void> deleteSingleQueryById (@Path("id") int id, @Path("resid") int resid);

    @POST("/add-execute-result")
    public ResponseEntity<Void> addExecuteResult (@Body SingleQueryResult singleQueryResult);

    @GET("/execute-single-query-by-id/{id}?resultId={resid}")
    public ResponseEntity<Void> executeSingleQueryById (@Path("id") int id, @Path("resid") int resid);

    @POST ("/add-get-single-query-by-id-result")
    public ResponseEntity<Void> addGetSingleQueryByIdResult (@Body SingleQueryResult singleQueryResult);

    @GET ("/get-single-query-by-id/{id}?resultId={resid}")
    public ResponseEntity<Void> getSingleQueryBiId (@Path("id") int id, @Path("resid") int resid);
}
