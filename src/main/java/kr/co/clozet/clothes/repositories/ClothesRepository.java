package kr.co.clozet.clothes.repositories;

import kr.co.clozet.clothes.domains.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


interface ClothesCustomRepository{

    @Query("SELECT a FROM Clothes a WHERE a.token IN :token and a.clothesClassification = :clothesClassification order by a.colors asc ")
    List<Clothes> findTop(@Param("token") String token, @Param("clothesClassification") String clothesClassification);

}

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long>, ClothesCustomRepository{
}
