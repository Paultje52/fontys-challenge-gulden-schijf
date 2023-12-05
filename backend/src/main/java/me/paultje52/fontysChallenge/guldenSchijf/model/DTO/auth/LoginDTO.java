package me.paultje52.fontysChallenge.guldenSchijf.model.DTO.auth;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @JsonDeserialize
public class LoginDTO {
    private String jwt;
    private String name;
}