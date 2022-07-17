package kr.co.clozet.users.controllers;

import io.swagger.annotations.*;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import kr.co.clozet.users.repositories.UserRepository;
import kr.co.clozet.users.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.PortUnreachableException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Log
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

    @GetMapping("/count")
    public ResponseEntity<Messenger> count() {
        return ResponseEntity.ok(service.count());
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

    @PostMapping(value = "/getToken") @ResponseBody
    public ResponseEntity<UserDTO> getToken(@RequestBody UserDTO userDTO) throws Exception{
        return ResponseEntity.ok(service.save1(userDTO));
    }

    @GetMapping("/findById") @ResponseBody
    public ResponseEntity<Optional<User>> findById(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.findById(userDTO));
    }

    @PostMapping("/token") @ResponseBody
    public ResponseEntity<Optional<User>> findByToken(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.findByToken(userDTO));
    }

    @PostMapping("/articlesByToken") @ResponseBody
    public ResponseEntity<List<Article>> articlesByToken(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.articlesByToken(userDTO));
    }

    @PostMapping("findByUsername") @ResponseBody
    public ResponseEntity<Optional<User>> findByUsername(@RequestBody String username) {
        return ResponseEntity.ok(service.findByUsername(username));
    }

    @GetMapping("/existsById/{userid}")
    public ResponseEntity<Messenger> existsById(@PathVariable String userid) {
        return ResponseEntity.ok(service.existsById(userid));
    }

    @PostMapping("/existsByUsername") @ResponseBody
    public ResponseEntity<Boolean> existsByUsername(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(repository.existsByUsername(userDTO.getUsername()));
    }

    @PostMapping("/existsByPhone") @ResponseBody
    public ResponseEntity<Boolean> existsByPhone(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(repository.existsByPhone(userDTO.getPhone()));
    }

    @PostMapping("/existsByEmail") @ResponseBody
    public ResponseEntity<Boolean> existsByEmail(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(repository.existsByEmail(userDTO.getEmail()));
    }

    @PostMapping("/existsByNickname") @ResponseBody
    public ResponseEntity<Boolean> existsByNickname(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(repository.existsByNickname(userDTO.getNickname()));
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
    public ResponseEntity<String> findUsername(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.find_id(userDTO).getUsername());
    }

    @PostMapping(value = "/findPw")
    public void findPwPOST(@RequestBody UserDTO user, HttpServletResponse response) throws Exception{
        System.out.println("아이디 : " + user.getUsername());
        System.out.println("email : " + user.getEmail());
        service.findPw(response, user);
    }

    @PatchMapping(value = "/update") @ResponseBody
    public void partialUpdate(@RequestBody UserDTO userDTO) throws Exception{
       service.partialUpdate(userDTO);
    }

    @DeleteMapping(value = "/delete") @ResponseBody
    public void delete(@RequestBody UserDTO userDTO) throws Exception{
        System.out.println(userDTO);
        service.delete(userDTO);
    }

    @DeleteMapping(value = "/deleteByUserId") @ResponseBody
    public void deleteByUserId(@RequestBody UserDTO userDTO) throws Exception{
        service.delete(userDTO);
    }



}

