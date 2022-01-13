package com.project.app;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.project.app.Entities.UserEntity;
import com.project.app.Repositories.UserRepository;

public class Auth {

    public static Authentication auth = SecurityContextHolder.getContext().getAuthentication();

}
