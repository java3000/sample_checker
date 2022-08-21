package ru.vk.competition.minbenchmark.controller;

import jdk.jfr.Frequency;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.vk.competition.minbenchmark.entity.SingleQuery;
import ru.vk.competition.minbenchmark.service.SingleQueryService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/single-query")
@RequiredArgsConstructor
public class SingleQueryController {

    private final SingleQueryService singleQueryService;

    @GetMapping("/add-new-query-result")
    public Mono<ResponseEntity<Void>> addResult(@PathVariable  String resultId,
                                                @PathVariable String code) {
        return singleQueryService.addResult(resultId, code);
    }

    @PostMapping("/add-new-query?resultId={id}")
    public Mono<ResponseEntity<Void>> addNewQuery (@PathVariable String id, @RequestBody String queryId, @RequestBody String query) {
        return singleQueryService.addNewQuery(id, queryId, query);
    }

    @PostMapping("/add-modify-result")
    public Mono<ResponseEntity<Void>> addModifyResult (@RequestBody String resultId, @RequestBody String code) {
        return singleQueryService.addModifyResult(resultId, code);
    }

    @PutMapping("/modify-single-query?resultId={id}")
    public Mono<ResponseEntity<Void>> modifySingleQuery (@PathVariable String id, @RequestBody String queryId, @RequestBody String query) {
        return singleQueryService.modifySingleQuery (id, queryId, query);
    }

    @PostMapping ("/add-delete-result")
    public Mono<ResponseEntity<Void>> addDeleteResult (@RequestBody String resultId, @RequestBody String code) {
        return singleQueryService.addDeleteResult(resultId, code);
    }

    @DeleteMapping("/delete-single-query-by-id/{id}?resultId={resid}")
    public Mono<ResponseEntity<Void>> deleteSingleQueryById (@PathVariable int id, @PathVariable int resid) {
        return singleQueryService.deleteSingleQueryById(id, resid);
    }

    @PostMapping("/add-execute-result")
    public Mono<ResponseEntity<Void>> addExecuteResult (@RequestBody String resultId, @RequestBody String code) {
        return singleQueryService.addExecuteResult(resultId, code);
    }

    @GetMapping("/execute-single-query-by-id/{id}?resultId={resid}")
    public Mono<ResponseEntity<Void>> executeSingleQueryById (@PathVariable int id, @PathVariable int resid) {
        return singleQueryService.executeSingleQueryById (id, resid);
    }

    @PostMapping ("/add-get-single-query-by-id-result")
    public Mono<ResponseEntity<Void>> addGetSingleQueryByIdResult (@RequestBody String resultId, @RequestBody String code) {
        return singleQueryService.addGetSingleQueryByIdResult (resultId, code);
    }

    @GetMapping ("/get-single-query-by-id/{id}?resultId={resid}")
    public Mono<ResponseEntity<Void>> getSingleQueryBiId (@PathVariable int id, @PathVariable int resid) {
        return singleQueryService.getSingleQueryBiId(id, resid);
    }
}
