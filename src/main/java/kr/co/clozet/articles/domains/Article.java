package kr.co.clozet.articles.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.co.clozet.files.domains.File;
import kr.co.clozet.users.domains.User;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * packageName:kr.co.clozet.auth.domains
 * fileName        :Article.java
 * author          : sungsuhan
 * date            :2022-05-18
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-18           sungsuhan      최초 생성
 **/
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
    @Column String comment;
    @Column String qna;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;






}
