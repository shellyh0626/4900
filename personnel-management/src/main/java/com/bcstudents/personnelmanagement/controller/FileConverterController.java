package com.bcstudents.personnelmanagement.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bcstudents.personnelmanagement.bean.FileConverter;
import com.bcstudents.personnelmanagement.config.PaginationConstant;
import com.bcstudents.personnelmanagement.service.IFileConverterService;
import com.bcstudents.personnelmanagement.util.ObjectToPdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Controller
public class FileConverterController {
    @Autowired
    IFileConverterService baseSerice;
    @Value("${upload.path}")
    private String uploadPath;


    // Query all
    @GetMapping("/fileConverters")
    public String list(Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum) {
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.CURRENT_NUM;
        }
        // Set pagination (current page and number of data entries per page)
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        // Search for data
        List<FileConverter> list = baseSerice.getAll();
        // Encapsulate the search results into a PageInfo object
        PageInfo<FileConverter> pageInfo=new PageInfo<>(list);
        // Use Model to pass objects to the page
        model.addAttribute("pageInfo",pageInfo);
        return "fileConverter/index";
    }

    // Arrive at the add page
    @GetMapping("/fileConverter")
    public String toAddPage(Model model) {
        // Arrive at the add page

        return "fileConverter/add";
    }

    // User addition
    @PostMapping("/fileConverter")
    public String add(FileConverter model) {
        // Save
        baseSerice.add(model);
        // Arrive at the list page
        return "redirect:/fileConverters";
    }

    // Arrive at the modify page
    @GetMapping("/fileConverter/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        FileConverter converter = baseSerice.getById(id);
        model.addAttribute("converter",converter);

        // Return to the modify page
        return "fileConverter/add";
    }

    // Modification requires submitting the ID
    @PutMapping("/fileConverter")
    public String update(FileConverter model) {
        baseSerice.update(model);
        return "redirect:/fileConverters";
    }

    // Delete
    @DeleteMapping("/fileConverter/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        baseSerice.deleteById(id);
        return "redirect:/fileConverters";
    }

    @GetMapping("/fileConverter/download")
    public ResponseEntity<InputStreamResource> downloadFile(Integer id,Integer type) throws Exception {
        // File path
        FileConverter model=baseSerice.getById(id);
        String txtName = FileUtil.getName(model.getFilePath());
        File txtFile = new File(uploadPath+"/upload/file/"+txtName);

        if(type==1){
            if (!txtFile.exists()) {
                // Handling for non-existent files
                return ResponseEntity.notFound().build();
            }

            InputStreamResource resource = new InputStreamResource(new FileInputStream(txtFile));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + txtName);

            return ResponseEntity.ok().headers(headers).contentLength(txtFile.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        }else if(type==2){
            String content=FileUtil.readString(txtFile, CharsetUtil.charset("utf-8"));
            File newFile=new File(uploadPath+"/upload/file/"+FileUtil.getPrefix(txtName)+".pdf");

            ObjectToPdf.strToPdf(content,newFile.getPath());

            InputStreamResource resource = new InputStreamResource(new FileInputStream(newFile));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + newFile.getName());

            return ResponseEntity.ok().headers(headers).contentLength(newFile.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);

        }else if(type==3){
            String content=FileUtil.readString(txtFile, CharsetUtil.charset("utf-8"));
            File newFile=new File(uploadPath+"/upload/file/"+FileUtil.getPrefix(txtName)+".docx");

            ObjectToPdf.strToDoc(content,newFile.getPath());

            InputStreamResource resource = new InputStreamResource(new FileInputStream(newFile));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + newFile.getName());

            return ResponseEntity.ok().headers(headers).contentLength(newFile.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);

        }else if(type==4){
            //String content=FileUtil.readString(txtFile, CharsetUtil.charset("utf-8"));
            File newFile=new File(uploadPath+"/upload/file/"+FileUtil.getPrefix(txtName)+".jpg");

            ObjectToPdf.strToJpg(txtFile.getPath(),newFile.getPath());

            InputStreamResource resource = new InputStreamResource(new FileInputStream(newFile));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + newFile.getName());

            return ResponseEntity.ok().headers(headers).contentLength(newFile.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);

        }else if(type==5){
            //String content=FileUtil.readString(txtFile, CharsetUtil.charset("utf-8"));
            File newFile=new File(uploadPath+"/upload/file/"+FileUtil.getPrefix(txtName)+".png");

            ObjectToPdf.strToPng(txtFile.getPath(),newFile.getPath());

            InputStreamResource resource = new InputStreamResource(new FileInputStream(newFile));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + newFile.getName());

            return ResponseEntity.ok().headers(headers).contentLength(newFile.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);

        }
        return null;

    }


}
