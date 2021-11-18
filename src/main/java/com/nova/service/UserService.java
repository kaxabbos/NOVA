package com.nova.service;

import com.nova.repo.RepoUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private RepoUsers repoUsers;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repoUsers.findByUsername(s);
    }
}
