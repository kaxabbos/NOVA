package com.nova.models;

import com.nova.models.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username, password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private long[] cart, buy;

    public Users() {
    }

    public Users(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long[] getCart() {
        return cart;
    }

    public void setCart(long[] cart) {
        this.cart = cart;
    }

    public long[] getBuy() {
        return buy;
    }

    public void setBuy(long[] buy) {
        this.buy = buy;
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getRole());
    }
}
