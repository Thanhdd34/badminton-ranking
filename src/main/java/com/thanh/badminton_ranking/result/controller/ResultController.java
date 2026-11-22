package com.thanh.badminton_ranking.result.controller;

import com.thanh.badminton_ranking.authentication.dto.request.RegisterRequest;
import com.thanh.badminton_ranking.result.dto.ResultRequest;
import com.thanh.badminton_ranking.result.dto.ResultResponse;
import com.thanh.badminton_ranking.result.service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<ResultResponse> createResult(@Valid @RequestBody ResultRequest request) {
        ResultResponse response = resultService.createResult(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResultResponse>> getAllResults() {

        List<ResultResponse> results =
                resultService.getAllResults();

        return ResponseEntity.ok(results);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getResultById(
            @PathVariable Long id) {

        ResultResponse response =
                resultService.getResultById(id);

        return ResponseEntity.ok(response);
    }
}
