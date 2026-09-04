package io.github.sibdevtools.common.api.rs;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.sibdevtools.common.api.dto.ErrorRsDto;
import lombok.Getter;

import java.io.Serializable;

/**
 * Standard response type for APIs without body
 *
 * @author sibmaks
 * @since 0.0.1
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardRs implements Serializable {
    /**
     * Flag which indicates whether the request was processed successfully
     */
    private final boolean success;
    /**
     * Error details, present only in fail responses
     */
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
