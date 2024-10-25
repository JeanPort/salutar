package br.com.isiflix.salutar.service.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Component
public class UploadServiceImpl implements IUploadService{


    @Override
    public String uploadFile(MultipartFile file) {

        try {
            String pastaDestino = "C:\\Users\\jean\\Desktop\\worckspace\\images";
            String extencao = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + extencao;
            Path path = Paths.get(pastaDestino + File.separator + newFileName);

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return newFileName;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
