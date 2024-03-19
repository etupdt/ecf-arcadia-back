package fr.ecf.arcadia.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;

@Component("fileService")
@Getter
@Setter
public class FileService {

    private String fileBasePath = "./webapps/ROOT/images/";

    public FileService() {

    }

    public String saveUploadedFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(fileBasePath + fileName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
        // return ServletUriComponentsBuilder.fromCurrentContextPath()
		// 	.path("/tmp/")
		// 	.path(fileName)
		// 	.toUriString();

    }

    public void download() {

    }

}
