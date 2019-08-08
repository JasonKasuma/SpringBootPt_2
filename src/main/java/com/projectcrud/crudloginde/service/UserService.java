package com.projectcrud.crudloginde.service;

import com.projectcrud.crudloginde.model.User;
import com.projectcrud.crudloginde.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
