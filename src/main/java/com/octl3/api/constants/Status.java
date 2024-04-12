package com.octl3.api.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Status {
    CREATED("Created"),
    PENDING("Pending"),
    SUBMITTED("Submitted"),
    REJECTED("Rejected"),
    ACCEPTED("Accepted"),
    REQUIRES_REVISION("Requires Revision");

    private final String value;

    public static boolean isValidStatus(String status) {
        return Arrays.stream(Status.values())
                .anyMatch(enumValue -> enumValue.value.equalsIgnoreCase(status));
    }
}
