package kr.co.clozet.articles.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.services.ArticleService;
import kr.co.clozet.auth.domains.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
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

    @GetMapping("/findAll")
    public ResponseEntity<List<Article>> findAll() {
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
    public ResponseEntity<Messenger> delete(@RequestBody Article article) {
        return ResponseEntity.ok(service.delete(article));
    }

    @PostMapping(value = "/join")
    public ResponseEntity<Messenger> save(@RequestBody ArticleDTO article) {
        System.out.println("게시글 정보: "+article.toString());//확인만 하려구.. 지워야함
        return ResponseEntity.ok(service.save(article));
    }

    @GetMapping("/findById/{article}")
    public Optional<Article> findById(@PathVariable String article) {
        return service.findById(article);
    }

    @GetMapping("/existsById/{article}")
    public boolean existsById(@PathVariable String article) {
        return service.existsById(article);
    }



}

