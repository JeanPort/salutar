package br.com.isiflix.salutar.controller;

import br.com.isiflix.salutar.dto.PathToFileDTO;
import br.com.isiflix.salutar.service.upload.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class UploadController {

    @Autowired
    private IUploadService service;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam(name = "arquivo") MultipartFile file) {
        String res = service.uploadFile(file);
        if (res != null) {
            return ResponseEntity.status(201).body(new PathToFileDTO(res));
        }
        return ResponseEntity.badRequest().build();
    }
}
