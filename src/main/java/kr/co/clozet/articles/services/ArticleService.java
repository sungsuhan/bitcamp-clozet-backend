package kr.co.clozet.articles.services;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.board.services
 * fileName        :ArticleService.java
 * author          : sungsuhan
 * date            :2022-05-09
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-09           sungsuhan      최초 생성
 **/
public interface ArticleService {
    List<Article> findAll();

    List<Article> findAllQna(ArticleDTO articleDTO);

    List<Article> findByUsernameToArticle(String username);

    List<Article> findAll(Sort sort);

    Article findByTitle(ArticleDTO articleDTO);

    Page<Article> findAll(Pageable pageable);

    List<Article> findMyQna(ArticleDTO articleDTO);

    long count();

    Messenger delete(ArticleDTO articleDTO);

    Messenger save(ArticleDTO article);

    Messenger saveQna(ArticleDTO article);

    Optional<Article> findById(ArticleDTO articleDTO);

    boolean existsById(String article);

    void partialUpdate(ArticleDTO articleDTO) throws Exception;

    List<Article> findByToken(UserDTO userDTO);

    List<Article> findByUserId(UserDTO userDTO);

    List<Article> findByUsername(String username);
}
