package kr.co.clozet.files.controllers;

import io.swagger.annotations.Api;
import kr.co.clozet.files.domains.File;
import kr.co.clozet.files.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/findAll")
    public List<File> findAll() {
        return service.findAll();
    }

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


}
