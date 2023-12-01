package me.paultje52.fontysChallenge.guldenSchijf.model.DTO.auth;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @JsonDeserialize
public class RenewResponseDTO {
    private final boolean success;
    private final String JWT;

    public RenewResponseDTO(boolean success) {
        this.success = success;
        this.JWT = null;
    }
    public RenewResponseDTO(boolean success, String JWT) {
        this.success = success;
        this.JWT = JWT;
    }
}