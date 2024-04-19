package com.octl3.api.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Status {
    CREATED("Created"),
    UPDATED("Updated"),
    PENDING("Pending"),
    SUBMITTED("Submitted"),
    REJECTED("Rejected"),
    ACCEPTED("Accepted"),
    ADDITIONAL("Additional");

    private final String value;

    public static boolean isValidStatus(String status) {
        return Arrays.stream(Status.values())
                .anyMatch(enumValue -> enumValue.value.equalsIgnoreCase(status));
    }

    public static boolean isValidLeaderStatus(String status) {
        return (status.equalsIgnoreCase(ACCEPTED.value) ||
                status.equalsIgnoreCase(REJECTED.value) ||
                status.equalsIgnoreCase(ADDITIONAL.value));
    }

    public static boolean isValidStatusForManagerUpdate(String status) {
        return (status.equalsIgnoreCase(CREATED.value) ||
                status.equalsIgnoreCase(UPDATED.value) ||
                status.equalsIgnoreCase(ADDITIONAL.value));
    }

    public static boolean isValidStatusForSubmit(String status) {
        return (status.equalsIgnoreCase(CREATED.value) ||
                status.equalsIgnoreCase(UPDATED.value));
    }

    public static boolean isValidStatusForLeaderUpdate(String status) {
        return (status.equalsIgnoreCase(PENDING.value));
    }
}
