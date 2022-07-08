package kr.co.clozet.articles.controllers;

import io.swagger.annotations.Api;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.services.ArticleService;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.common.util.MD5Generator;
import kr.co.clozet.files.domains.File;
import kr.co.clozet.files.domains.FileDTO;
import kr.co.clozet.files.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * packageName:kr.co.clozet.board.controllers
 * fileName        :ArticleController.java
 * author          : sungsuhan
 * date            :2022-05-09
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-09           sungsuhan      최초 생성
 **/
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags ="articles")
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService service;
    private final FileService fileService;

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<List<Article>> findByUsernameToArticle(@PathVariable("username") String username) {
        return ResponseEntity.ok(service.findByUsernameToArticle(username));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Article>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/findAllQna")
    public ResponseEntity<List<Article>> findAllQna() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/findAll/sort")
    public ResponseEntity<List<Article>> findAll(Sort sort) {
        return ResponseEntity.ok(service.findAll(sort));
    }

    @GetMapping("/findAll/pageable")
    public ResponseEntity<Page<Article>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> delete(@RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.ok(service.delete(articleDTO));
    }

    @PostMapping(value = "/join")
    public ResponseEntity<Messenger> save(@RequestBody ArticleDTO article) {
        System.out.println("게시글 정보: " + article.toString());//확인만 하려구.. 지워야함
        return ResponseEntity.ok(service.save(article));
    }

    @PostMapping(value = "/comment")
    public ResponseEntity<Article> findByTitle(@RequestBody ArticleDTO article) {
        System.out.println("게시글 정보: " + article.toString());//확인만 하려구.. 지워야함
        return ResponseEntity.ok(service.findByTitle(article));
    }

    @PostMapping(value = "/joinQna")
    public ResponseEntity<Messenger> saveQna(@RequestBody ArticleDTO article) {
        System.out.println("QnA 정보: " + article.toString());//확인만 하려구.. 지워야함
        return ResponseEntity.ok(service.save(article));
    }

    @GetMapping("/findById")
    @ResponseBody
    public ResponseEntity<Optional<Article>> findById(ArticleDTO articleDTO) {
        return ResponseEntity.ok(service.findById(articleDTO));
    }

    @GetMapping("/existsById/{article}")
    public boolean existsById(@PathVariable String article) {
        return service.existsById(article);
    }

    @PatchMapping(value = "/update") @ResponseBody
    public ResponseEntity<Integer> partialUpdate(@RequestBody final ArticleDTO articleDTO) {
        return ResponseEntity.ok(service.partialUpdate(articleDTO));
    }



}