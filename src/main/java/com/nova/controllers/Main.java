package com.nova.controllers;

import com.nova.models.Users;
import com.nova.repo.RepoComments;
import com.nova.repo.RepoGames;
import com.nova.repo.RepoUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Main {

    @Autowired
    RepoUsers repoUsers;

    @Autowired
    RepoGames repoGames;

    @Autowired
    RepoComments repoComments;

    @Value("${upload.path}")
    String uploadPath;

    String checkUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            if (userDetail != null) {
                Users userFromDB = repoUsers.findByUsername(userDetail.getUsername());
                return String.valueOf(userFromDB.getRole());
            }
            return "NOT";
        }
        return "NOT";
    }
    Users checkUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            if (userDetail != null) {
                return repoUsers.findByUsername(userDetail.getUsername());
            }
        }
        return null;
    }
}
