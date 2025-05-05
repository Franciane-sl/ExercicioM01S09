package br.com.futuroDev.APISustentavel.Service;

import br.com.futuroDev.APISustentavel.Model.Entity.User;
import br.com.futuroDev.APISustentavel.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado."));
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public UserDetails loadUserByUsername(String username) {
        return userDetailsService.loadUserByUsername(username);
    }

    public void registerUser(User user){
        if (!userRepository.existsByUsername(user.getUsername())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }else{
            throw new RuntimeException("O usuário já exite.");
        }
    }
}
