package com.github.simplemocks.common.api.rs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.simplemocks.common.api.dto.ErrorRsDto;
import lombok.Getter;

import java.io.Serializable;

/**
 * Standard response type for APIs without body
 *
 * @author sibmaks
 * @since 0.0.4
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardRs implements Serializable {
    private final boolean success;
    private final ErrorRsDto error;

    /**
     * Construct standard success response
     */
    public StandardRs() {
        this.success = true;
        this.error = null;
    }

    /**
     * Construct standard fail response
     *
     * @param error happened error
     */
    public StandardRs(ErrorRsDto error) {
        this.success = false;
        this.error = error;
    }

}
