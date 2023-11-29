package me.paultje52.fontysChallenge.guldenSchijf.model.DTO.auth;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @JsonDeserialize
public class LoginBodyDTO {
    private String userName;
    private String password;
}