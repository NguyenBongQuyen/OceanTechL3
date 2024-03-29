package com.octl3.api.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

}
