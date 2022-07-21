package kr.co.clozet.articles.services;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;


public interface ArticleService {
    List<Article> findAll();

    List<Article> findAllQna(ArticleDTO articleDTO);

    List<Article> findByUsernameToArticle(String username);

    List<Article> findAll(Sort sort);

    List<Article> findByTitle(ArticleDTO articleDTO);

    Page<Article> findAll(Pageable pageable);

    List<Article> findMyQna(ArticleDTO articleDTO);

    List<Article> findComment(ArticleDTO articleDTO);

    long count();

    Messenger save(ArticleDTO article);

    void saveQna(ArticleDTO article) throws Exception;

    Optional<Article> findById(ArticleDTO articleDTO);

    boolean existsById(String article);

    void partialUpdate(ArticleDTO articleDTO) throws Exception;

    List<Article> findByToken(UserDTO userDTO);

    List<Article> findByUserId(UserDTO userDTO);

    List<Article> findByUsername(String username);

    void deleteArticle(Long articleId);

}
