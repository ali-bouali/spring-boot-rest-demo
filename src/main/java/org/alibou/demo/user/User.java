package org.alibou.demo.user;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.common.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @SuperBuilder
  @Entity
  @Table(name = "_user")
  @DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
  public class User extends BaseEntity implements UserDetails {

    @Column(updatable = false, nullable = false, unique = true)
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    @Column(length = 1000)
    private String firstname;
    private String lastname;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return new ArrayList<>();
    }

    @Override
    public String getPassword() {
      return password;
    }

    @Override
    public String getUsername() {
      return email;
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
      return enabled;
    }
}
