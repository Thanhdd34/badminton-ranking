package com.thanh.badminton_ranking.common.exception;

public class DuplicateResultException extends RuntimeException{
    public DuplicateResultException(){
        super("Result already exists for this athlete, tournament and event");
    }
}
