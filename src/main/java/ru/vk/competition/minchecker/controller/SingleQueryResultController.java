package ru.vk.competition.minchecker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minchecker.service.SingleQueryService;

@RestController
@RequestMapping("/api/single-query")
@RequiredArgsConstructor
public class SingleQueryResultController {

    private final SingleQueryService singleQueryService;

    @GetMapping("/add-new-query-result")
    public ResponseEntity<Void> addResult(@PathVariable  String resultId,
                                                @PathVariable String code) {
        System.out.println("1");
        return singleQueryService.addResult(resultId, code);
    }

    @PostMapping("/add-new-query?resultId={id}")
    public ResponseEntity<Void> addNewQuery (@PathVariable String id, @RequestBody String queryId, @RequestBody String query) {
        System.out.println("2");
        return singleQueryService.addNewQuery(id, queryId, query);
    }

    @PostMapping("/add-modify-result")
    public ResponseEntity<Void> addModifyResult (@RequestBody String resultId, @RequestBody String code) {
        System.out.println("3");
        return singleQueryService.addModifyResult(resultId, code);
    }

    @PutMapping("/modify-single-query?resultId={id}")
    public ResponseEntity<Void> modifySingleQuery (@PathVariable String id, @RequestBody String queryId, @RequestBody String query) {
        System.out.println("4");
        return singleQueryService.modifySingleQuery (id, queryId, query);
    }

    @PostMapping ("/add-delete-result")
    public ResponseEntity<Void> addDeleteResult (@RequestBody String resultId, @RequestBody String code) {
        System.out.println("5");
        return singleQueryService.addDeleteResult(resultId, code);
    }

    @DeleteMapping("/delete-single-query-by-id/{id}?resultId={resid}")
    public ResponseEntity<Void> deleteSingleQueryById (@PathVariable int id, @PathVariable int resid) {
        System.out.println("6");
        return singleQueryService.deleteSingleQueryById(id, resid);
    }

    @PostMapping("/add-execute-result")
    public ResponseEntity<Void> addExecuteResult (@RequestBody String resultId, @RequestBody String code) {
        System.out.println("7");
        return singleQueryService.addExecuteResult(resultId, code);
    }

    @GetMapping("/execute-single-query-by-id/{id}?resultId={resid}")
    public ResponseEntity<Void> executeSingleQueryById (@PathVariable int id, @PathVariable int resid) {
        System.out.println("8");
        return singleQueryService.executeSingleQueryById (id, resid);
    }

    @PostMapping ("/add-get-single-query-by-id-result")
    public ResponseEntity<Void> addGetSingleQueryByIdResult (@RequestBody String resultId, @RequestBody String code) {
        System.out.println("9");
        return singleQueryService.addGetSingleQueryByIdResult (resultId, code);
    }

    @GetMapping ("/get-single-query-by-id/{id}?resultId={resid}")
    public ResponseEntity<Void> getSingleQueryBiId (@PathVariable int id, @PathVariable int resid) {
        System.out.println("10");
        return singleQueryService.getSingleQueryBiId(id, resid);
    }
}