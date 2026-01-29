package com.example.SpringBootMVC.utils;

import com.example.SpringBootMVC.role.entity.Role;
import com.example.SpringBootMVC.user.UserDetailsImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public class Utils {

    public AuthUserDTO getUserAuthData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            Long userIDFromAuth = userDetails.getId(); // assign the ID here
            String mail = userDetails.getUsername();
            List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(a -> a.getAuthority())
                    .toList();

            return new AuthUserDTO(userIDFromAuth, mail, roles);
            /*return userIDFromAuth;*/
        }
        return null; // in case auth is null or principal is not UserDetailsImpl
    }
    /**
     * Checks whether the authenticated user is authorized to access or modify
     * a resource that belongs to the given user.
     * Authorization is granted if:
     * <ul>
     *   <li>The authenticated user's ID matches the target user ID, or</li>
     *   <li>The authenticated user has the ROLE_ADMIN role.</li>
     * </ul>
     *
     * @param userId the ID of the resource owner
     * @return true if the user is authorized, false otherwise
     */
    public Boolean isAuthorizedForThis(Long userId) {

        return Objects.equals(getUserAuthData().getId(), userId) || getUserAuthData().getRoles().contains("ROLE_ADMIN");
    }
}
