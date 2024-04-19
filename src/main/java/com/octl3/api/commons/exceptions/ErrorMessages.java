package com.octl3.api.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages implements ErrorMessage {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad request"),
    INVALID_VALUE(400_001, "Invalid value"),
    INVALID_STATUS(400_002, "Invalid status"),
    INVALID_DATE(400, "Invalid date"),
    INVALID_FORMAT(400, "Invalid format"),
    DATE_CAN_NOT_IN_THE_FUTURE(400, "The date can not be in the future"),
    INVALID_DATE_OF_BIRTH(400, "Invalid date of birth because age must be between 18-60"),
    SAVE_DATABASE_ERROR(400_003, "Save database error"),
    USERNAME_LOGIN_FAIL(400_004, "Login fail! Username incorrect."),
    PASSWORD_LOGIN_FAIL(400_005, "Login fail! Password incorrect."),
    DELETE_ERROR(400_006, "Can not delete this resource"),
    CONVERT_JSON_ERROR(400_007, "Convert json error"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_ALLOW(403_001, "Not allow access this resource"),
    NOT_ALLOW_UPDATE(403_002,"Can not update the record in this state"),
    NOT_FOUND(404, "Resource not found"),
    NOT_FOUND_LEADER_ID(404_001, "Leader Id not found"),
    NOT_FOUND_EMPLOYEE_ID(404_002, "Employee Id not found"),
    NOT_FOUND_CERTIFICATE_ID(404, "Certificate id not found"),
    NOT_FOUND_PROFILE_END_ID(404, "Profile end id not found"),
    NOT_FOUND_RELATIONSHIP_ID(404, "Relationship id not found"),
    NOT_FOUND_REGISTRATION_ID(404, "Registration id not found"),
    DUPLICATE_DATA(405, "Data duplicate"),
    DUPLICATE_USERNAME(405_001, "Username duplicate"),
    DUPLICATE_EMAIL(405_002, "Email duplicate"),
    DUPLICATE_PHONE(405, "Phone duplicate"),
    DUPLICATE_CITIZEN_ID(405, "Citizen id duplicate"),
    FILE_UPLOAD_ERROR(406, "File upload error"),
    FILE_DELETE_ERROR(407, "File delete error");

    private final int code;
    private final String message;
}
