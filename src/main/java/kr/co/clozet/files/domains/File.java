package kr.co.clozet.files.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.co.clozet.articles.domains.Article;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName:kr.co.clozet.auth.domains
 * fileName        :Board.java
 * author          : sungsuhan
 * date            :2022-05-18
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-18           sungsuhan      최초 생성
 **/
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name="files")
public class File {

    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.AUTO) private long fileId;
    @Column private String pictureName;
    @Column private String picture;
    @Column private long size;


    @JsonManagedReference
    @OneToMany(mappedBy = "file", cascade = CascadeType.REMOVE)
    List<Article> articles = new ArrayList<>();


}
