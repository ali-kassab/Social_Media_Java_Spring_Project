package com.example.SpringBootMVC.utils;

import com.example.SpringBootMVC.user.UserDetailsImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Utils {
    private Long userIDFromAuth;

    public Long getUserIDFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            userIDFromAuth = userDetails.getId(); // assign the ID here
            return userIDFromAuth;
        }
        return null; // in case auth is null or principal is not UserDetailsImpl
    }
}
