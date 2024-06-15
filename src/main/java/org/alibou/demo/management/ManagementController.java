package org.alibou.demo.management;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
@RequiredArgsConstructor
// ROLE_admin
@PreAuthorize("hasAnyRole('admin', 'super_admin')")
public class ManagementController {
}
