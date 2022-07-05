package kr.co.clozet.users.controllers;

import io.swagger.annotations.*;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import kr.co.clozet.users.repositories.UserRepository;
import kr.co.clozet.users.services.UserService;
import kr.co.clozet.users.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.controllers
 * fileName        :UserController.java
 * author          : sungsuhan
 * date            :2022-05-03
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-03           sungsuhan      최초 생성
 **/
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags ="users")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserRepository repository;
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    @ApiOperation(value ="${UserController.login")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 422, message = "유효하지 않은 아이디 / 비밀번호")
    })
    public ResponseEntity<UserDTO> login(@ApiParam("Login User") @RequestBody UserDTO user) {
        return ResponseEntity.ok(service.login(user));
    }

    @GetMapping("/logout")
    public ResponseEntity<Messenger> logout() {
        return ResponseEntity.ok(service.logout());
    }

    @PutMapping("/update")
    public ResponseEntity<Messenger> update(@RequestBody User user) {
        return ResponseEntity.ok(service.update(user));
    }

    // Embeded Method
    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/findAll/sort")
    public ResponseEntity<List<User>> findAll(Sort sort) {
        return ResponseEntity.ok(service.findAll(sort));
    }

    @GetMapping("/findAll/pageable")
    public ResponseEntity<Page<User>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }
    @PostMapping("/token")
    public ResponseEntity<User> findByToken(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(service.findByToken(userDTO));
    }
    @GetMapping("/count")
    public ResponseEntity<Messenger> count() {
        return ResponseEntity.ok(service.count());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> delete(@RequestBody User user) {
        return ResponseEntity.ok(service.delete(user));
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Messenger> deleteAll() {
        return ResponseEntity.ok(service.deleteAll());
    }

    @PostMapping("/join")
    @ApiOperation(value = "${UserController.join}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 403, message = "승인거절"),
            @ApiResponse(code = 422, message = "중복된 ID")
    })
    public ResponseEntity<Messenger> save(@ApiParam("Join User") @RequestBody UserDTO user) {
        System.out.println("회원가입 정보: "+user.toString()); // 확인만 하려구...지워야함
        return ResponseEntity.ok(service.save(user));
    }

    @GetMapping("/findById/{userid}")
    public ResponseEntity<Optional<User>> findById(@PathVariable String userid) {
        return ResponseEntity.ok(service.findById(userid));
    }

    @GetMapping("/existsById/{userid}")
    public ResponseEntity<Messenger> existsById(@PathVariable String userid) {
        return ResponseEntity.ok(service.existsById(userid));
    }

    @GetMapping("/findHan")
    public ResponseEntity<List<User>> findHan() {
        return ResponseEntity.ok(repository.findHan());
    }

    @GetMapping("/findPhoneByHan")
    public ResponseEntity<String []> findPhoneByHan() {
        return ResponseEntity.ok(repository.findPhoneByHan());
    }

    @GetMapping("/findTitleByUserId")
    public ResponseEntity<String []> findTitleByUserId() {
        return ResponseEntity.ok(repository.findTitleByUserId());
    }

    @PostMapping("/findUsername") @ResponseBody
    public ResponseEntity<String> findUsername(@RequestBody UserDTO user) {
        return ResponseEntity.ok(service.findUsername(user).getUsername());
    }
    @PostMapping(value = "/findPw")
    public void findPwPOST(@RequestBody UserDTO user, HttpServletResponse response) throws Exception{
        System.out.println("아이디 : " + user.getUsername());
        System.out.println("email : " + user.getEmail());
        service.findPw(response, user);
    }


}

