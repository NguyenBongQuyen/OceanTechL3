package com.octl3.api.utils;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import static com.octl3.api.constants.FileConst.PATH_UPLOAD_FOLDER;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadFile {
    public static String uploadImage(MultipartFile fileImage, String prefix) {
        try {
            String fileName = fileImage.getOriginalFilename();
            // lay dinh dang
            String extension = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));
            // tao ten moi
            String newFileName = prefix + UUID.randomUUID() + extension;
            String filePath = PATH_UPLOAD_FOLDER + newFileName;
            File newFile = new File(filePath);
            fileImage.transferTo(newFile);
            return filePath;
        } catch (Exception e) {
            throw new OctException(ErrorMessages.FILE_UPLOAD_ERROR);
        }
    }

    public static void deleteImage(String fileName) {
        try {
            String filePath = PATH_UPLOAD_FOLDER + fileName;
            Path path = Paths.get(filePath);
            Files.delete(path);
        } catch (Exception e) {
            throw new OctException(ErrorMessages.FILE_DELETE_ERROR);
        }
    }
}
