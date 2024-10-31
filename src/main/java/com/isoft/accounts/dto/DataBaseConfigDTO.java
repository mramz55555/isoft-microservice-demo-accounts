package com.isoft.accounts.dto;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(
        prefix = "isoft"
)
@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataBaseConfigDTO {
    private Map<String, String> datasource;
    private List<String> maintainers;
    @Setter(AccessLevel.NONE)
    private String manager;

    public void setManager(String manager) {
        this.manager = manager.trim();
    }
}
