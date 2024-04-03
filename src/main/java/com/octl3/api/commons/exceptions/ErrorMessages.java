package com.octl3.api.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages implements ErrorMessage {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad request"),
    INVALID_VALUE(400_001, "Invalid value"),
    SAVE_DATABASE_ERROR(400_002, "Save database error"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Resource not found"),
    DUPLICATE_DATA(405, "Data duplicate"),
    FILE_UPLOAD_ERROR(406, "File upload error"),
    FILE_DELETE_ERROR(406, "File delete error"),
    NOT_ALLOW(407, "Not allow"),
    CONVERT_JSON_ERROR(408, "Convert json error");

    private final int code;
    private final String message;
}
