package kr.co.clozet.articles.controllers;

import io.swagger.annotations.Api;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.repositories.ArticleRepository;
import kr.co.clozet.articles.services.ArticleService;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags ="articles")
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService service;
    private final ArticleRepository repository;

    @PostMapping(value = "/write")
    public ResponseEntity<Messenger> save(@RequestBody ArticleDTO article) {
        System.out.println("게시글 정보: " + article.toString());
        return ResponseEntity.ok(service.save(article));
    }

    @PostMapping("/findByTokenToArticle") @ResponseBody
    public ResponseEntity<List<Article>> findByTokenToArticle(@RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.ok(repository.findByTokenToArticle(articleDTO.getToken()));
    }

    @PostMapping(value = "/joinQna") @ResponseBody
    public void saveQna(@RequestBody ArticleDTO article) throws Exception{
        System.out.println("QnA 정보: " + article.toString());
        service.saveQna(article);
    }

    @PostMapping(value = "/findByQnaDateASC")
    public ResponseEntity<List<Article>> findByQnaDateASC(@RequestBody ArticleDTO article) {
        return ResponseEntity.ok(repository.findByQnaDateASC(article.getOpen()));
    }

    @PostMapping("/findMyQna") @ResponseBody
    public ResponseEntity<List<Article>> findMyQna(@RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.ok(service.findMyQna(articleDTO));
    }

    @DeleteMapping(value = "/deleteArticle") @ResponseBody
    public void delete1(@RequestBody ArticleDTO articleId){
        service.deleteArticle(articleId.getArticleId());
    }

    @GetMapping("/findAllArticle")
    public ResponseEntity<List<Article>> findAllArticles() {
        return ResponseEntity.ok(repository.findAllArticle());
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Article>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/findComment") @ResponseBody
    public ResponseEntity<List<Article>> findComment(@RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.ok(service.findComment(articleDTO));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return  ResponseEntity.ok(service.count());
    }

    @DeleteMapping("/delete") @ResponseBody
    public void delete(@RequestBody ArticleDTO articleDTO) {
        repository.deleteById(articleDTO.getArticleId());
    }

    @DeleteMapping("/deleteComment") @ResponseBody
    public void deleteComment(@RequestBody ArticleDTO articleDTO) {
        repository.deleteComment(articleDTO.getToken(), articleDTO.getArticleId());
    }


    @PostMapping(value = "/qnaList")
    public ResponseEntity<List<Article>> qnaList(@RequestBody ArticleDTO article) {
        return ResponseEntity.ok(service.findAllQna(article));
    }


    @PostMapping(value = "/comment")
    public ResponseEntity<List<Article>> findByTitle(@RequestBody ArticleDTO article) {
        System.out.println("게시글 정보: " + article.toString());//확인만 하려구.. 지워야함
        return ResponseEntity.ok(service.findByTitle(article));
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
    public Integer read(@RequestBody ArticleDTO articleDTO) {
        ResponseEntity.ok(repository.updateView(articleDTO.getArticleId()));
        Article article = new Article();
        return article.getView();
    }

    @DeleteMapping(value = "/tokenDelete/{token}{articleId}")
    public void delete(@PathVariable("token") String token, @PathVariable("articleId") long articleId ) throws Exception{
        repository.deleteArticle(token, articleId);
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<List<Article>> findByUsername(@RequestBody String username) {
        return ResponseEntity.ok(service.findByUsernameToArticle(username));
    }




}