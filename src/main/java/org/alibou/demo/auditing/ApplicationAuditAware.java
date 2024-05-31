//package org.alibou.demo.auditing;
//
//import java.util.Optional;
//import org.springframework.data.domain.AuditorAware;
//
//public class ApplicationAuditAware implements AuditorAware<Long> {
//
//  @Override
//  public Optional<Long> getCurrentAuditor() {
//    Authentication authentication =
//      SecurityContextHolder
//        .getContext()
//        .getAuthentication();
//    if (authentication == null ||
//      !authentication.isAuthenticated() ||
//      authentication instanceof AnonymousAuthenticationToken
//    ) {
//      return Optional.empty();
//    }
//
//    UserEntity userPrincipal = (UserEntity) authentication.getPrincipal();
//    return Optional.ofNullable(userPrincipal.getId());
//  }
//}