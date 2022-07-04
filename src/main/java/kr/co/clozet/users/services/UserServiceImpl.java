package kr.co.clozet.users.services;

import kr.co.clozet.auth.configs.AuthProvider;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.auth.exception.SecurityRuntimeException;
import kr.co.clozet.common.blank.StringUtils;
import kr.co.clozet.users.domains.Role;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import kr.co.clozet.users.repositories.UserRepository;
import kr.co.clozet.common.dataStructure.Box;
import lombok.RequiredArgsConstructor;
import org.apache.commons.mail.HtmlEmail;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static kr.co.clozet.common.lambdas.Lambda.*;

/**
 * packageName:kr.co.clozet.services
 * fileName        :UserServiceImpl.java
 * author          : sungsuhan
 * date            :2022-05-03
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-03           sungsuhan      최초 생성
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthProvider provider;
    private final ModelMapper modelMapper;


    @Override
    public UserDTO login(UserDTO paramUser) {
        try{
            UserDTO returnUser = new UserDTO();
            String username = paramUser.getUsername();
            User findUser = repository.findByUsername(username).orElse(null);
            if(findUser != null){
                boolean checkPassword = encoder.matches(paramUser.getPassword(), findUser.getPassword());
                if(checkPassword){
                    returnUser = modelMapper.map(findUser, UserDTO.class);
                    String token = provider.createToken(username, returnUser.getRoles());
                    returnUser.setToken(token);
                    findUser = modelMapper.map(returnUser, User.class); // 토큰
                    repository.save(findUser); // 토큰
                }else{
                    String token = "FAILURE";
                    returnUser.setToken(token);
                }
            }
            return returnUser;
        }catch (Exception e){
            throw new SecurityRuntimeException("유효하지 않은 아이디/비밀번호", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public List<User> findAll(Sort sort) {
        List<User> list = repository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        return list;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Messenger count() {
        return Messenger.builder().message(string(repository.count())).build();
    }

//    public Messenger update(User user) {
//        repository.update(user);
//        return Messenger.builder().build();
//    }


    @Override
    public Messenger delete(UserDTO user) {
        repository.findById(user.getUserId()).ifPresent(repository::delete);
        return Messenger.builder().message("삭제 완료").build();
    }

    @Override
    public Messenger deleteAll() {
        repository.deleteAll();
        return Messenger.builder().message("전체 삭제").build();
    }

    @Override
    public Messenger save(UserDTO user) {
        System.out.println("서비스로 전달된 회원가입 정보: "+user.toString());
        String result = "";
        if (repository.findByUsername(user.getUsername()).isEmpty()) {
            List<Role> list = new ArrayList<>();
            list.add(Role.USER);
            repository.save(User.builder()
                    .username(user.getUsername())
                    .name(user.getName())
                    .birth(user.getBirth())
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .password(encoder.encode(user.getPassword()))
                    .roles(list).build());
            result = "SUCCESS";
        } else {
            result = "FAIL";
        }
        return Messenger.builder().message(result).build();
    }

    @Override
    public Optional<User> findById(long userId) {
        return repository.findById(userId);
    }

    @Override
    public Messenger existsById(String userid) {
        return repository.existsById(longParse(userid))
                ? Messenger.builder().message("EXIST").build()
                : Messenger.builder().message("NOT_EXIST").build(); //userid 타입이 다름
    }

    // custom
    @Override
    public List<User> findByUserName(String name) {
        List<User> ls = repository.findAll();
        Box<String, User> box = new Box<>();
        // ls = box.findByUserName(ls, name);
        // ls.stream().filter(...)
        return null;
    }

    @Override
    public Messenger logout() {
        return Messenger.builder().build();
    }

    @Override
    public UserDTO findUsername(UserDTO user) {
        String result = "";
        try {
            result= repository.findUsername(user.getName(), user.getEmail());
            user.setUsername(result);

        } catch(Exception e) {

            e.printStackTrace();
        }

        return user;

    }
    //비밀번호 찾기 이메일발송
    @Override
    public void sendEmail(UserDTO user, String div) throws Exception {
        // Mail Server 설정
        String charSet = "utf-8";
        String hostSMTP = "smtp.gmail.com"; //네이버 이용시 smtp.naver.com
        String hostSMTPid = "swhanssu@gmail.com";
        String hostSMTPpwd = "owmhrcwfvoihuwke";

        // 보내는 사람 EMail, 제목, 내용
        String fromEmail = "swhanssu@gmail.com";//"보내는 사람 이메일주소(받는 사람 이메일에 표시됨)";
        String fromName = "CLOZET";//"프로젝트이름 또는 보내는 사람 이름";
        String subject = "임시비밀번호 발금";
        String msg = "임시비밀번호";

        if(div.equals("findpw")) {
            subject = "CLOZET 임시 비밀번호 입니다.";
            msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
            msg += "<h3 style='color: blue;'>";
            msg += user.getUsername() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
            msg += "<p>임시 비밀번호 : ";
            msg += user.getPassword() + "</p></div>";
        }

        // 받는 사람 E-Mail 주소
        String mail = user.getEmail();
        try {
            HtmlEmail email = new HtmlEmail();
            email.setDebug(true);
            email.setCharset(charSet);
            email.setSSLOnConnect(true);
            email.setHostName(hostSMTP);
            email.setSmtpPort(587); //네이버 이용시 587

            email.setAuthentication(hostSMTPid, hostSMTPpwd);
            email.setStartTLSEnabled(true);
            email.addTo(mail, charSet);
            email.setFrom(fromEmail, fromName, charSet);
            email.setSubject(subject);
            email.setHtmlMsg(msg);
            email.send();
        } catch (Exception e) {
            System.out.println("메일발송 실패 : " + e);
        }
    }

    //비밀번호찾기
    @Override
    public void findPw(HttpServletResponse response, UserDTO user) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        User returnUser = new User();
        String username = user.getUsername();
        PrintWriter out = response.getWriter();
        // 가입된 아이디가 없으면
        if(username == null) {
            out.print("등록되지 않은 아이디입니다.");
            out.close();
        }
        // 가입된 이메일이 아니면
        else if(!user.getEmail().equals(user.getEmail())) {
            out.print("등록되지 않은 이메일입니다.");
            out.close();
        }else {
            // 임시 비밀번호 생성
            String pw = "";
            for (int i = 0; i < 12; i++) {
                pw += (char) ((Math.random() * 26) + 97);
            }
            user.setPassword(pw);
            // 비밀번호 변경
            String newPw = returnUser.getPassword();
            repository.save(returnUser);

            // 비밀번호 변경 메일 발송
            sendEmail(user, "findpw");

            out.print("이메일로 임시 비밀번호를 발송하였습니다.");
            out.close();
        }
    }

    @Override @Transactional
    public int partialUpdate(final UserDTO userDTO) {
        Optional<User> originUser = repository.findById(userDTO.getUserId());

        User user = originUser.get();
        if(StringUtils.isNotBlank(userDTO.getName())) user.setName(userDTO.getName());
        if(StringUtils.isNotBlank(userDTO.getBirth())) user.setBirth(userDTO.getBirth());
        if(StringUtils.isNotBlank(userDTO.getNickname())) user.setNickname(userDTO.getNickname());
        if(StringUtils.isNotBlank(userDTO.getPhone())) user.setPhone(userDTO.getPhone());
        if(StringUtils.isNotBlank(userDTO.getEmail())) user.setEmail(userDTO.getEmail());
        if(StringUtils.isNotBlank(userDTO.getPassword())) user.setPassword(userDTO.getPassword());
        if(StringUtils.isNotBlank(userDTO.getUsername())) user.setUsername(userDTO.getUsername());
        repository.save(user);
        return 1;
    }



}
