package cn.com.senfun.common;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetailsImpl extends org.springframework.security.core.userdetails.User {

    private String salt;

    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities, String salt) {
        super(username, password, authorities);
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
