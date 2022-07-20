package kr.co.clozet.clothes.controllers;

import io.swagger.annotations.Api;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.clothes.domains.Clothes;
import kr.co.clozet.clothes.domains.ClothesDTO;
import kr.co.clozet.clothes.repositories.ClothesRepository;
import kr.co.clozet.clothes.services.ClothesService;
import kr.co.clozet.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName:kr.co.clozet.closet.controllers
 * fileName        :ClothesController.java
 * author          : sungsuhan
 * date            :2022-05-29
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-29           sungsuhan      최초 생성
 **/
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "clothes")
@RestController
@RequiredArgsConstructor
@RequestMapping("/clothes")
public class ClothesController {

    private final ClothesService service;
    private final ClothesRepository repository;

    @GetMapping("/findAll")
    public ResponseEntity<List<Clothes>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
  @PostMapping("/findTop") @ResponseBody
    public ResponseEntity<List<Clothes>> findTop(@RequestBody ClothesDTO clothesDTO) {
        return ResponseEntity.ok(repository.findTop(clothesDTO.getToken(), clothesDTO.getClothesClassification()));
    }

}



