package kr.co.clozet.clothes.controllers;

import io.swagger.annotations.Api;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.auth.domains.Messenger;
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

    @PostMapping(value = "/join")
    public ResponseEntity<Messenger> save(@RequestBody ClothesDTO clothesDTO) {
        System.out.println("옷 정보: " + clothesDTO.toString());//확인만 하려구.. 지워야함
        return ResponseEntity.ok(service.save(clothesDTO));
    }

    @DeleteMapping(value = "/delete") @ResponseBody
    public void delete(@RequestBody ClothesDTO clothesDTO){
        service.delete(clothesDTO.getClothesId());
    }

    @PostMapping("/findTop")
    public ResponseEntity<List<Clothes>> findTop() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/findBottom")
    public ResponseEntity<List<Clothes>> findBottom() {
        return ResponseEntity.ok(service.findAll());
    }












}



