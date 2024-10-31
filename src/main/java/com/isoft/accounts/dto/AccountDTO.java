package com.isoft.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "account",
        description = "account information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO extends BaseDTO {
    private Long customerId;
    @Schema(
            example = "credit"
    )
    private @NotBlank(
            message = "account type must not be blank"
    ) String accountType;
    @Schema(
            description = "current branch address"
    )
    private @NotBlank(
            message = "branch address must not be blank"
    ) String branchAddress;
}
