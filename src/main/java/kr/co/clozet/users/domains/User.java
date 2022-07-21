package kr.co.clozet.users.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.clothes.domains.Clothes;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(length = 1200) private String token;

    public User(long userId) {
        this.userId = userId;
    }

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Article> articles = new ArrayList<>();

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Clothes> clothes = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    public List<Role> roles;



}
