package com.thanh.badminton_ranking.common.exception.dto;

public class ResultNotFoundException extends RuntimeException{
    public ResultNotFoundException(Long id){
        super("Result not found with id: " + id);
    }
}
