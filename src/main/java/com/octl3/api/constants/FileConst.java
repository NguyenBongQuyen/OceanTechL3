package com.octl3.api.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileConst {
    public static final String PATH_UPLOAD_FOLDER = System.getProperty("user.dir") + "\\src\\main\\resources\\templates\\upload\\";
    public static final String EMPLOYEE_IMAGE_PREFIX = "employee_image_";
}
