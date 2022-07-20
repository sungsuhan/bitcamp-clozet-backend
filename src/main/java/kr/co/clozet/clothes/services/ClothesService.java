package kr.co.clozet.clothes.services;

import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.clothes.domains.Clothes;
import kr.co.clozet.clothes.domains.ClothesDTO;

import java.util.List;

/**
 * packageName:kr.co.clozet.closet.services
 * fileName        :ClothesService.java
 * author          : sungsuhan
 * date            :2022-05-29
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-29           sungsuhan      최초 생성
 **/
public interface ClothesService {
    List<Clothes> findAll();

    Messenger save(ClothesDTO clothesDTO);

    void delete(Long clothesId);
}
