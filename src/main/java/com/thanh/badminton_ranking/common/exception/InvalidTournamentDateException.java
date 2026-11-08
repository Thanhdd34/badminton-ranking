package com.thanh.badminton_ranking.common.exception;

public class InvalidTournamentDateException extends  RuntimeException{
    public InvalidTournamentDateException(){
        super("End date must not be before start date");
    }
}
