package io.github.sibdevtools.common.api.dto;

import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.io.Serializable;

/**
 * Error data transfer object, used as an error description in API responses
 *
 * @param systemCode code of the system that raised the error
 * @param code       error code, unique in scope of the system
 * @param title      short human-readable error title
 * @param message    detailed human-readable error message
 * @author sibmaks
 * @since 0.0.1
 */
@Builder
public record ErrorRsDto(
        @Nonnull
        String systemCode,
        @Nonnull
        String code,
        @Nonnull
        String title,
        @Nonnull
        String message
) implements Serializable {
}
