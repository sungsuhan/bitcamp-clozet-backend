package kr.co.clozet.articles.services;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.repositories.ArticleRepository;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.common.blank.StringUtils;
import kr.co.clozet.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public Messenger delete(ArticleDTO articleDTO) {
        repository.findById(articleDTO.getArticleId()).ifPresent(repository::delete);
        return Messenger.builder().message("삭제").build();
    }
    @Override
    public List<Article> findByUsernameToArticle(String username) {

        return repository.findByUsernameToArticle(username);
    }
    @Override
    public Messenger save(ArticleDTO article) {
        System.out.println("서비스로 전달된 게시글 정보: "+article.toString());
        String result = "";
        if (repository.findByTitle(article.getTitle()).isEmpty()) {
            repository.save(Article.builder()
                    .title(article.getTitle())
                    .writtenDate(article.getWrittenDate())
                    .open(article.getOpen())
                    .content(article.getContent())
                    .picture(article.getPicture())
                    .height(article.getHeight())
                    .weight(article.getWeight())
                    .comment(article.getComment())
                    .build());
            result = "SUCCESS";
        } else {
            result = "FAIL";
        }
        return Messenger.builder().message(result).build();
    }

    @Override
    public Optional<Article> findById(ArticleDTO articleDTO) {
        return repository.findById(articleDTO.getArticleId());
    }

    @Override
    public boolean existsById(String article) {
        return repository.existsById(0L);
    }

    @Override @Transactional
    public int partialUpdate(final ArticleDTO articleDTO) {
        Optional<Article> originArticle = repository.findById(articleDTO.getArticleId());

        Article article = originArticle.get();
        if(StringUtils.isNotBlank(articleDTO.getTitle())) article.setTitle(articleDTO.getTitle());
        if(StringUtils.isNotBlank(articleDTO.getWrittenDate())) article.setWrittenDate(articleDTO.getWrittenDate());
        if(StringUtils.isNotBlank(articleDTO.getOpen())) article.setOpen(articleDTO.getOpen());
        if(StringUtils.isNotBlank(articleDTO.getContent())) article.setContent(articleDTO.getContent());
        if(StringUtils.isNotBlank(articleDTO.getPicture())) article.setPicture(articleDTO.getPicture());
        if(StringUtils.isNotBlank(articleDTO.getHeight())) article.setHeight(articleDTO.getHeight());
        if(StringUtils.isNotBlank(articleDTO.getWeight())) article.setWeight(articleDTO.getWeight());
        if(StringUtils.isNotBlank(articleDTO.getComment())) article.setComment(articleDTO.getComment());
        repository.save(article);
        return 1;
    }

}

