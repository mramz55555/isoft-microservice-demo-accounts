package com.isoft.accounts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.security.SecureRandom;
import java.util.Optional;

@Configuration
public class AppConfig {
    public AppConfig() {
    }

    @Bean
    public AuditorAware<String> getAuditAware() {
        return new AuditAwareImp();
    }

    private static class AuditAwareImp implements AuditorAware<String> {
        private AuditAwareImp() {
        }

        public Optional<String> getCurrentAuditor() {
            StringBuilder builder = new StringBuilder();
            SecureRandom secureRandom = new SecureRandom();

            for (int i = 0; i < secureRandom.nextInt(5) + 3; ++i) {
                builder.append((char) (secureRandom.nextInt(26) + 65));
            }

            return Optional.of(builder.toString());
        }
    }
}

