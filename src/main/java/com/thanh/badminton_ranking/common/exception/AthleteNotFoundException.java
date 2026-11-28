package com.thanh.badminton_ranking.common.exception;

public class AthleteNotFoundException extends  RuntimeException
{
     public AthleteNotFoundException(String id){
         super("Athlete not found with id: " + id);
     }
}
