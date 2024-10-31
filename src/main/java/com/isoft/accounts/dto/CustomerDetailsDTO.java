package com.isoft.accounts.dto;

import com.isoft.accounts.dto.client.CardsDto;
import com.isoft.accounts.dto.client.LoansDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(name = "customer details",
        description = "customer related details")
public class CustomerDetailsDTO extends BaseDTO {
    @Schema(
            description = "all related accounts",
            example = "[{account1 pattern},{account2 pattern},...]"
    )
    @Setter(AccessLevel.NONE)
    private final List<AccountDTO> accountDTOList = new ArrayList<>();
    @Schema(
            description = "all related cards",
            example = "[{card1 pattern},{card2 pattern},...]"
    )
    @Setter(AccessLevel.NONE)
    private final List<CardsDto> cardDTOList = new ArrayList<>();
    @Schema(
            description = "all related loans",
            example = "[{loan1 pattern},{loan2 pattern},...]"
    )
    @Setter(AccessLevel.NONE)
    private final List<LoansDto> loanDTOList = new ArrayList<>();
    @Schema(description = "character length has limitation.pay attention!",
            maxLength = 255,
            example = "Ali hosseini")
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

    public void setCustomer(CustomerDTO customerDTO) {
        this.mobileNumber = customerDTO.getMobileNumber();
        this.email = customerDTO.getEmail();
        this.name = customerDTO.getName();
    }
}
