package kr.co.clozet.clothes.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.users.domains.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.AUTO) private long clothesId;
    @Column private String clothesClassification;
    @Column private String colors;
    @Column private String weathers;
    @Column private String styles;
    @Column private String events;
    @Column(length = 1200) private String token;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "clothes", cascade = CascadeType.REMOVE)
    List<Article> articles = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
