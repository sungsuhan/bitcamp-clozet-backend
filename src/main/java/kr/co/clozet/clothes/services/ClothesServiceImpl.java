package kr.co.clozet.clothes.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.clothes.domains.Clothes;
import kr.co.clozet.clothes.domains.ClothesDTO;
import kr.co.clozet.clothes.repositories.ClothesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static kr.co.clozet.common.lambdas.Lambda.string;

/**
 * packageName:kr.co.clozet.closet.services
 * fileName        :ClothesServiceImpl.java
 * author          : sungsuhan
 * date            :2022-05-29
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-29           sungsuhan      최초 생성
 **/
@Service
@RequiredArgsConstructor
public class ClothesServiceImpl implements ClothesService {

    private final ClothesRepository repository;
    @Override
    public List<Clothes> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Clothes> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<Clothes> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Messenger count() {
        return Messenger.builder().message(string(repository.count())).build();
    }


    @Override
    public Optional<Clothes> findByDate(String date) {
        return Optional.empty();
    }

    @Override
    public Messenger existsById(String userid) {
        return null;
    }

    @Override
    public Messenger getOne(Long id) {
        return null;
    }

    @Override
    public Optional<Clothes> findByUserId(String userId) {
        return null;
    }

    @Override
    public Messenger update(Clothes clothes) {
        return null;
    }
    public Messenger save(ClothesDTO clothesDTO) {
        System.out.println("서비스로 전달된 옷 정보: " + clothesDTO.toString());
        String result = "";
        repository.save(Clothes.builder()
                .clothesClassification(clothesDTO.getClothesClassification())
                .colors(clothesDTO.getColors())
                .token(clothesDTO.getToken())
                .build());
        result = "SUCCESS";
        return Messenger.builder().message(result).build();
    }

    @Override @Transactional
    public void delete(Long clothesId){
        Optional<Clothes> optClothes = repository.findById(clothesId);
        if (optClothes.isPresent()){
            Clothes clothes = optClothes.get();
            repository.delete(clothes);
        }
    }


}





