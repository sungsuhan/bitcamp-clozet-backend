package kr.co.clozet.articles.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService service;

    @GetMapping("/findAll")
    public List<Article> findAll() {
        return service.findAll();
    }

    @GetMapping("/findAll/sort")
    public List<Article> findAll(Sort sort) {
        return service.findAll(sort);
    }

    @GetMapping("/findAll/pageable")
    public Page<Article> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody Article article) {
        return service.delete(article);
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

