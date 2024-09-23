package com.github.sibdevtools.common.api.rs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.sibdevtools.common.api.dto.ErrorRsDto;
import lombok.Getter;

import java.io.Serializable;

/**
 * Standard response type for all APIs
 *
 * @author sibmaks
 * @since 0.0.1
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardBodyRs<T extends Serializable> extends StandardRs {
    private final T body;

    /**
     * Construct standard success empty response
     */
    public StandardBodyRs() {
        this.body = null;
    }

    /**
     * Construct standard success response
     *
     * @param body response body
     */
    public StandardBodyRs(T body) {
        this.body = body;
    }

    /**
     * Construct standard fail response
     *
     * @param error happened error
     */
    public StandardBodyRs(ErrorRsDto error) {
        super(error);
        this.body = null;
    }
}
