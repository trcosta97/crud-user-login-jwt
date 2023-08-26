package br.com.hearMeOut.authentication.service;

import br.com.hearMeOut.authentication.domain.address.Address;
import br.com.hearMeOut.authentication.domain.user.User;
import br.com.hearMeOut.authentication.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        User persistedUser = userRepository.save(user);
        return userRepository.save(persistedUser);
    }
}
