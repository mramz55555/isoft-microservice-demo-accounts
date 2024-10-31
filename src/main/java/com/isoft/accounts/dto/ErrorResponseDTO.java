package com.isoft.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(
        name = "Error DTO",
        description = "model for showing error"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    @Schema(
            example = "https://localhost/api/v1/create"
    )
    private String apiPath;
    @Schema(
            example = "404",
            description = "error http code"
    )
    private int errorCode;
    @Schema(
            description = "error details",
            example = "error message"
    )
    private String errorMessage;
    @Schema(
            description = "time that error happened"
    )
    private LocalDateTime errorTime;

}
