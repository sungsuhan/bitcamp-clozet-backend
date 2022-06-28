package kr.co.clozet.articles.services;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.repositories.ArticleRepository;
import kr.co.clozet.articles.services.ArticleService;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.Role;
import kr.co.clozet.users.domains.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.board.services
 * fileName        :ArticleServiceImpl.java
 * author          : sungsuhan
 * date            :2022-05-09
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-09           sungsuhan      최초 생성
 **/
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repository;


    @Override
    public List<Article> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Article> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public String delete(Article article) {
        repository.delete(article);
        return "";
    }

//    @Override
//    public Messenger save(ArticleDTO article) {
//        System.out.println("서비스로 전달된 게시글 정보: "+article.toString());
//        String result = "";
//        if (repository.findByArticleId(article.getArticleId()).isEmpty()) {
//            repository.save(Article.builder()
//                    .(article.getArticleId())
//                    .name(user.getName())
//                    .birth(user.getBirth())
//                    .nickname(user.getNickname())
//                    .email(user.getEmail())
//                    .phone(user.getPhone())
//                    .password(encoder.encode(user.getPassword()))
//                    .build());
//            result = "SUCCESS";
//        } else {
//            result = "FAIL";
//        }
//        return Messenger.builder().message(result).build();
//    }

    @Override
    public Optional<Article> findById(String article) {
        return repository.findById(0L);
    }

    @Override
    public boolean existsById(String article) {
        return repository.existsById(0L);
    }
}
