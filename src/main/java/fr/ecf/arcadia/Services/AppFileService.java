package fr.ecf.arcadia.Services;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Component("appFileService")
@Getter
@Setter
public class AppFileService {
    
    @Autowired
    private Environment environment;    

    private String fileBasePath = "." + File.separator + "webapps" + File.separator + "ROOT" + File.separator + "images" + File.separator ;

    private static final Logger logger = LoggerFactory.getLogger(AppFileService.class);
    
    public AppFileService() {
    }

    public String saveUploadedFile(MultipartFile file, String objectName) {

        String hash = "";
        String fileName = "";

        try {
            hash = new BigInteger(1, MessageDigest.getInstance("MD5").digest(file.getBytes())).toString(16);
            fileName = hash + "_" + objectName + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        } catch (NoSuchAlgorithmException e) {
            logger.error("no such algorithme");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        logger.info("hashage ok " + hash);

        try {
            Files.copy(file.getInputStream(), new File(fileBasePath + fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            logger.error("error lors de la copie du fichier " + fileName);
            e.printStackTrace();
        }

        logger.info("file copie ok");

        return fileName;

    }

}
