package com.bcstudents.personnelmanagement.controller;
import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {
    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping(value = "/upload")
    @ResponseBody
    public Map<String,Object> upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        Map<String,Object> map=new HashMap<>();
        // Image validation
        // Check if the image is empty, its size, whether it's an image being uploaded, image type
        if (file.isEmpty()) {
            map.put("code","500");
            map.put("msg","Upload your file");
            return map;
        }
        // Get information or data related to coccyx
        String ext= FileUtil.getSuffix(file.getOriginalFilename());
        // Generate a new filename (to prevent overwriting due to duplicate names)
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // New name
        String newName = uuid +"."+ ext;

        // Specify the path where it is being saved
        String filePath = uploadPath + "/upload/file/" + newName;
        // Check if the file exists
        if(!new File(filePath).getParentFile().exists()){
            // Create parent folder
            new File(filePath).getParentFile().mkdirs();
        }
        // Save file to local
        file.transferTo(new File(filePath));

        map.put("code","200");
        map.put("path","/file/show/"+newName);
        map.put("name",file.getOriginalFilename());
        map.put("msg","");
        return map;
    }


    @GetMapping("/show/{name}")
    public ResponseEntity<Resource> show(@PathVariable String name) {
        try {
            // Path
            Path file = Paths.get(uploadPath + "/upload/file/" + name);
            // Retrieve file
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
