package kr.co.clozet.articles.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.co.clozet.clothes.domains.Clothes;
import kr.co.clozet.files.domains.File;
import kr.co.clozet.users.domains.User;
import lombok.*;
import org.springframework.stereotype.Component;
import javax.persistence.*;


@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name="articles")
public class Article {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.AUTO) private long articleId;
    @Column private String title;
    @Column(name = "written_date") private String writtenDate;
    @Column private String open;
    @Column private String content;
    @Column private String picture;
    @Column private String height;
    @Column private String weight;
    @Column private String nickname;
    @Column String comment;
    @Column String qna;
    @Column(columnDefinition = "integer default 0", nullable = false) private int view;
    @Column(length = 1200) private String token;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;




}
