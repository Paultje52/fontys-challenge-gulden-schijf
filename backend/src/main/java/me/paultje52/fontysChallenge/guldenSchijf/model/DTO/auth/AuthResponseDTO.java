package me.paultje52.fontysChallenge.guldenSchijf.model.DTO.auth;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @JsonDeserialize
public class AuthResponseDTO {
    private final boolean success;
    private final String JWT;
    private final String name;

    public AuthResponseDTO(boolean success) {
        this.success = success;
        this.JWT = null;
        this.name = null;
    }
    public AuthResponseDTO(boolean success, String JWT, String name) {
        this.success = success;
        this.JWT = JWT;
        this.name = name;
    }
}