package com.github.sibdevtools.common.api.dto;

import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.io.Serializable;

/**
 * @author sibmaks
 * @since 0.0.1
 */
@Builder
public record ErrorRsDto(@Nonnull String systemCode,
                         @Nonnull String code,
                         @Nonnull String title,
                         @Nonnull String message) implements Serializable {
}
