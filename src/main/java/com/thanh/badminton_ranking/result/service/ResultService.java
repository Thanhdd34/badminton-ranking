package com.thanh.badminton_ranking.result.service;

import com.thanh.badminton_ranking.result.dto.ResultRequest;
import com.thanh.badminton_ranking.result.dto.ResultResponse;

public interface ResultService {
    ResultResponse createResult(ResultRequest resultRequest);
}
