package kr.co.clozet.articles.controllers;

import io.swagger.annotations.Api;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.repositories.ArticleRepository;
import kr.co.clozet.articles.services.ArticleService;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.common.dataStructure.Box2;
import kr.co.clozet.users.domains.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


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
    private final ArticleRepository repository;
    private final Box2 box;

//    @GetMapping("/findByUsername")
//    public ResponseEntity<List<Article>> findByUsernameToArticle(@RequestBody String username) {
//        return ResponseEntity.ok(service.findByUsernameToArticle(username));
//    }

    @GetMapping("/findByTokenToArticle") @ResponseBody
    public ResponseEntity<String []> findByTokenToArticle(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(repository.findByTokenToArticle(userDTO.getToken()));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Article>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    @PostMapping("/findMyQna")
    public ResponseEntity<List<Article>> findMyQna(ArticleDTO articleDTO) {
        return ResponseEntity.ok(service.findMyQna(articleDTO));
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

    @DeleteMapping("/delete") @ResponseBody
    public void delete(@RequestBody ArticleDTO articleDTO) throws Exception{
        System.out.println(articleDTO);
        service.delete(articleDTO);
    }

    @PostMapping(value = "/join")
    public ResponseEntity<Messenger> save(@RequestBody ArticleDTO article) {
        System.out.println("게시글 정보: " + article.toString());//확인만 하려구.. 지워야함
        return ResponseEntity.ok(service.save(article));
    }

    @PostMapping(value = "/qnaList")
    public ResponseEntity<List<Article>> qnaList(@RequestBody ArticleDTO article) {
            return ResponseEntity.ok(service.findAllQna(article));
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

    @GetMapping("/findById") @ResponseBody
    public ResponseEntity<Optional<Article>> findById(@RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.ok(service.findById(articleDTO));
    }

    @PostMapping("/findByUserId") @ResponseBody
    public ResponseEntity<List<Article>> findByUserId(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.findByUserId(userDTO));
    }

    @GetMapping("/existsById/{article}")
    public boolean existsById(@PathVariable String article) {
        return service.existsById(article);
    }

    @PatchMapping(value = "/update") @ResponseBody
    public void partialUpdate(@RequestBody final ArticleDTO articleDTO) throws Exception{
        service.partialUpdate(articleDTO);
    }

    @GetMapping("/posts/{title}") @ResponseBody
    public Integer read(@PathVariable("title") String title) {
        ResponseEntity.ok(repository.updateView(title));
        Article article = new Article();
        return article.getView();
    }

    @DeleteMapping(value = "/tokenDelete/{token}{title}")
    public void delete(@PathVariable("token") String token, @PathVariable("title") String title ) throws Exception{
        repository.deleteArticle(token, title);
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<List<Article>> findByUsername(@RequestBody String username) {
        return ResponseEntity.ok(service.findByUsernameToArticle(username));
    }








}