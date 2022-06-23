package kr.co.clozet.clothes.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.co.clozet.closets.domains.Closet;
import kr.co.clozet.users.domains.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * packageName:kr.co.clozet.closet.domains
 * fileName        :Clothes.java
 * author          : sungsuhan
 * date            :2022-05-29
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-29           sungsuhan      최초 생성
 **/
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name="clothes")
public class Clothes {

    @Id
    @Column(name = "clothes_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) private long clothesId;
    @Column private String clothesClassification;
    @Column private String colors;
    @Column private String weathers;
    @Column private String styles;
    @Column private String events;

    @JsonBackReference // json 꼬리물기 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closet_id")
    private Closet closet;

    @JsonBackReference // json 꼬리물기 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;




}
