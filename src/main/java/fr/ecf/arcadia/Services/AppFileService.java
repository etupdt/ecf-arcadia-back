package fr.ecf.arcadia.Services;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
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

    private String fileBasePath =  "webapps" + File.separator + "ROOT" + File.separator + "images" + File.separator ;

    private static final Logger logger = LoggerFactory.getLogger(AppFileService.class);
    
    public AppFileService() {
    }

    public String saveUploadedFile(MultipartFile file, String objectName) {

        String fileName = "";
        File directory = new File("./");
        
        logger.info("===========> create " + directory.getAbsolutePath() + "====" + fileBasePath + "=====" + file.getOriginalFilename() );
        logger.info("===========> images path ==== " + environment.getProperty("server.imagesFilePath"));

        try {
            // fileName = directory.getAbsolutePath().substring(0, directory.getAbsolutePath().length() - 1) + fileBasePath + file.getOriginalFilename();
            // fileName = directory.getAbsolutePath().substring(0, directory.getAbsolutePath().length() - 1) + environment.getProperty("server.imagesFilePath") + file.getOriginalFilename();
            fileName = environment.getProperty("server.imagesFilePath") + file.getOriginalFilename();
            file.transferTo(new File(fileName));
            logger.info("file copie ok");
        } catch (IOException e) {
            logger.error("error lors de la copie du fichier " + fileName);
            e.printStackTrace();
        }

        return fileName;

    }

    public Boolean deleteFile(String fileName) {

        File directory = new File("./");
        
        logger.info("===========> delete " + directory.getAbsolutePath() + "====" + fileBasePath + "=====" + fileName );

        File fileToDelete = FileUtils.getFile(directory.getAbsolutePath().substring(0, directory.getAbsolutePath().length() - 1) + environment.getProperty("server.imagesFilePath") + fileName);
        return FileUtils.deleteQuietly(fileToDelete);

    }

}
