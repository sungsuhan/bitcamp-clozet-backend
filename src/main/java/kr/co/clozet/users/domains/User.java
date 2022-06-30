package kr.co.clozet.users.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.clothes.domains.Clothes;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName:kr.co.clozet.domains
 * fileName        :User.java
 * author          : sungsuhan
 * date            :2022-05-03
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-03           sungsuhan      최초 생성
 **/
@ToString
@Setter // modelMapper 를 사용하기 위해

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
// 컴포넌트는 프로퍼티와 메소드의 집합
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO) private long userId;
    @Column private String username;
    @Column private String password;
    @Column private String name;
    @Column private String birth;
    @Column private String nickname;
    @Column private String email;
    @Column private String phone;
    @Column private String token;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Article> articles = new ArrayList<>();

    @JsonManagedReference // json 꼬리물기 방지
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Clothes> clothes = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    public List<Role> roles;



}
