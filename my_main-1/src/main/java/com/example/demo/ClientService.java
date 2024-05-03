package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public Optional<Client> getClientByUsername(String username) {
        return clientRepository.findByUsername(username);
    }
    
    public void addLoginCredentialsToClient(Long clientId, String username, String password) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setUsername(username);
            String encodedPassword = passwordEncoder.encode(password);
            client.setPassword(encodedPassword);
            clientRepository.save(client);
        } else {
            throw new IllegalArgumentException("Client with id " + clientId + " not found.");
        }
    }
    
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
    public void addClient(Client client) {
        try {
            clientRepository.save(client);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    public void updateClient(Client client) {
        try {
            clientRepository.save(client);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteClient(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
    
    public boolean authenticateUser(String username, String password) {
        return true; 
    }
    
}