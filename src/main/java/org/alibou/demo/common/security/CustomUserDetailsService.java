package org.alibou.demo.common.security;

import lombok.RequiredArgsConstructor;
import org.alibou.demo.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserDetails usr= userRepository.findUserByEmail(email).orElseThrow(()->new UsernameNotFoundException("the user is not found  with the mail " +email ));
  return  usr ;
  }
}