package kr.co.clozet.auth.domains;

import kr.co.clozet.users.domains.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName:kr.co.clozet.security.domains
 * fileName        :UserDetailsImpl.java
 * author          : sungsuhan
 * date            :2022-05-23
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-23           sungsuhan      최초 생성
 **/
@Getter
@RequiredArgsConstructor
public class Auth implements UserDetails {

    private final long userid;
    private final String username;
    @JsonIgnore private final String password;
    private final String name;
    private final String email;

    public static Auth build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
        return new Auth(user.getUserId(), user.getUsername(), user.getPassword(),
                user.getName(), user.getEmail(), authorities);
    }

    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
