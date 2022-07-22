package kr.co.clozet.clothes.controllers;

import io.swagger.annotations.Api;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.clothes.domains.Clothes;
import kr.co.clozet.clothes.domains.ClothesDTO;
import kr.co.clozet.clothes.repositories.ClothesRepository;
import kr.co.clozet.clothes.services.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


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

    @PostMapping(value = "/join")
    public ResponseEntity<Messenger> save(@RequestBody ClothesDTO clothesDTO) {
        System.out.println("옷 정보: " + clothesDTO.toString());//확인만 하려구.. 지워야함
        return ResponseEntity.ok(service.save(clothesDTO));
    }

    @DeleteMapping(value = "/delete") @ResponseBody
    public void delete(@RequestBody ClothesDTO clothesDTO){
        service.delete(clothesDTO.getClothesId());
    }


  @PostMapping("/findTop") @ResponseBody
    public ResponseEntity<List<Clothes>> findTop(@RequestBody ClothesDTO clothesDTO) {
        return ResponseEntity.ok(repository.findTop(clothesDTO.getToken(), clothesDTO.getClothesClassification()));
    }

    @GetMapping("/findAll/sort")
    public ResponseEntity<List<Clothes>> findAll(Sort sort) {return ResponseEntity.ok(service.findAll(sort));}

    @GetMapping("/findAll/pageable")
    public ResponseEntity<Page<Clothes>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));}

    @GetMapping("/count")
    public ResponseEntity<Messenger> count() {return ResponseEntity.ok(service.count());}


    @GetMapping("/findById/{userId}")
    public ResponseEntity<Optional<Clothes>> findByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(service.findByUserId(userId));}


    @GetMapping("/getOne/{id}")
    public ResponseEntity<Messenger> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));}

    @PutMapping("/update")
    public ResponseEntity<Messenger> update(@RequestBody Clothes clothes) {
        return ResponseEntity.ok(service.update(clothes));
    }

}



