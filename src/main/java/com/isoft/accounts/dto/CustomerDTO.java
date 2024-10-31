package com.isoft.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Schema(
        name = "customer object 's real name",
        description = "customer dto object."
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO extends BaseDTO {
    @Schema(
            description = "character length has limitation.pay attention!",
            maxLength = 255,
            example = "Ali hosseini"
    )
    private @NotBlank(
            message = "name  must not be blank"
    ) @Length(
            max = 255
    ) String name;
    @Schema(
            example = "ali@yahoo.com"
    )
    private @Email(
            message = "please provide a valid email"
    ) @NotBlank(
            message = "email must not be blank"
    ) String email;
    @Schema(
            description = "exactly 10 number",
            example = "0123456789"
    )
    private @Pattern(
            regexp = "^[0-9]{11}$",
            message = "please provide a valid mobile number"
    ) String mobileNumber;
    @Schema(
            description = "all related accounts",
            example = "[{account1 pattern},{account2 pattern},...]"
    )
    private List<AccountDTO> accountDTOList = new ArrayList();
}
