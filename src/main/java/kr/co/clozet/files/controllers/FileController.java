package kr.co.clozet.files.controllers;

import io.github.classgraph.Resource;
import io.swagger.annotations.Api;
import kr.co.clozet.files.domains.File;
import kr.co.clozet.common.dataStructure.Box2;
import kr.co.clozet.files.properties.FileProperties;
import kr.co.clozet.files.services.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * packageName:kr.co.clozet.controllers
 * fileName        :BoardController.java
 * author          : sungsuhan
 * date            :2022-05-04
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-04           sungsuhan      최초 생성
 **/
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags ="files")
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService service;
    private final FileProperties fileProperties;

    @GetMapping("/findAll/sort")
    public List<File> findAll(Sort sort) {
        return service.findAll(sort);
    }

    @GetMapping("/findAll/pageable")
    public Page<File> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody File file) {
        return service.delete(file);
    }

    @GetMapping("/findById/{file}")
    public Optional<File> findById(@PathVariable String file) {
        return service.findById(file);
    }

    @GetMapping("/existsById/{file}")
    public boolean existsById(@PathVariable String file) {
        return service.existsById(file);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestPart List<MultipartFile> files) throws IOException{
        final String location = fileProperties.getLocation();
        final List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String fileUrl = fileProperties.getLocation() + fileName;
            Long fileSize = file.getSize();

            fileList.add(File.builder()
                    .pictureName(fileName)
                    .picture(fileUrl)
                    .size(fileSize)
                    .build());
        }
        service.saveFileList(fileList);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @GetMapping("/findAll")
    public ResponseEntity<?> getFiles(){
        List<File> files = service.findAll();
        return ResponseEntity.ok(files);
    }







}
