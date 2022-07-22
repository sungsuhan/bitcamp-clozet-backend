package kr.co.clozet.users.controllers;

import io.swagger.annotations.*;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import kr.co.clozet.users.repositories.UserRepository;
import kr.co.clozet.users.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags ="users")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Log
public class UserController {

    private final UserService service;
    private final UserRepository repository;

    @PostMapping("/join")
    @ApiOperation(value = "${UserController.join}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 403, message = "승인거절"),
            @ApiResponse(code = 422, message = "중복된 ID")
    })
    public ResponseEntity<Messenger> save(@ApiParam("Join User") @RequestBody UserDTO user) {
        System.out.println("회원가입 정보: "+user.toString());
        return ResponseEntity.ok(service.save(user));
    }

    @PostMapping("/login")
    @ApiOperation(value ="${UserController.login")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 422, message = "유효하지 않은 아이디 / 비밀번호")
    })
    public ResponseEntity<UserDTO> login(@ApiParam("Login User") @RequestBody UserDTO user) {
        return ResponseEntity.ok(service.login(user));
    }

    @PatchMapping(value = "/update") @ResponseBody
    public void update(@RequestBody UserDTO userDTO) throws Exception{
        service.update(userDTO);
    }

    @DeleteMapping(value = "/delete") @ResponseBody
    public void delete(@RequestBody UserDTO userDTO) throws Exception{
        System.out.println(userDTO);
        service.delete(userDTO);
    }

    @PostMapping("/username") @ResponseBody
    public ResponseEntity<String> findUsername(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.find_id(userDTO).getUsername());
    }

    @PostMapping(value = "/password")
    public void findPwP(@RequestBody UserDTO user, HttpServletResponse response) throws Exception{
        System.out.println("아이디 : " + user.getUsername());
        System.out.println("email : " + user.getEmail());
        service.findPw(response, user);
    }

    @PostMapping("/exists") @ResponseBody
    public ResponseEntity<Boolean> existsByUsername(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(repository.existsByUsername(userDTO.getUsername()));
    }

    @PostMapping("/token") @ResponseBody
    public ResponseEntity<Optional<User>> findByToken(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.findByToken(userDTO));
    }

}