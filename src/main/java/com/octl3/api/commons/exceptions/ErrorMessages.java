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
    USERNAME_LOGIN_FAIL(400_003, "Login fail! Username incorrect."),
    PASSWORD_LOGIN_FAIL(400_004, "Login fail! Password incorrect."),
    DELETE_ERROR(400_005, "Can not delete this resource"),
    CONVERT_JSON_ERROR(400_006, "Convert json error"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_ALLOW(403_001, "Not allow access this resource"),
    NOT_FOUND(404, "Resource not found"),
    DUPLICATE_DATA(405, "Data duplicate"),
    DUPLICATE_USERNAME(405_001, "Username duplicate"),
    DUPLICATE_EMAIL(405_002, "Email duplicate"),
    FILE_UPLOAD_ERROR(406, "File upload error"),
    FILE_DELETE_ERROR(407, "File delete error");

    private final int code;
    private final String message;
}
