package com.treinos.treinos.services;

import com.treinos.treinos.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;  // A entidade User vai ser encapsulada dentro dessa classe

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Usando a role do User para definir as permissões
        return List.of(new SimpleGrantedAuthority(user.getRole())); // Role do usuário
    }

    @Override
    public String getPassword() {
        return user.getPassword();  // Usando a senha do User
    }

    @Override
    public String getUsername() {
        return user.getEmail();  // O email vai ser usado como username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Customizar se a conta expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Customizar se a conta está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Customizar se as credenciais expiram
    }

    @Override
    public boolean isEnabled() {
        return true;  // Customizar se o usuário está habilitado
    }

    // Método para acessar diretamente o objeto User
    public User getUser() {
        return this.user;
    }

    // Métodos para acessar dados específicos do usuário
    public Integer getId() {
        return user.getId();
    }

    public String getName() {
        return user.getName();
    }
}
