package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private ClientService clientService;

    public boolean authenticateUser(String username, String password) {
        return clientService.authenticateUser(username, password);
    }
}