package com.thanh.badminton_ranking.common.exception;

public class AthleteNotFoundException extends  RuntimeException
{
     public AthleteNotFoundException(Long id){
         super("Athlete not found with id: " + id);
     }
}
