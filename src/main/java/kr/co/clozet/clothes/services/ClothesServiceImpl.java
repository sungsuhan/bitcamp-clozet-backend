package kr.co.clozet.clothes.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.clothes.domains.Clothes;
import kr.co.clozet.clothes.domains.ClothesDTO;
import kr.co.clozet.clothes.repositories.ClothesRepository;
import kr.co.clozet.clothes.services.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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





