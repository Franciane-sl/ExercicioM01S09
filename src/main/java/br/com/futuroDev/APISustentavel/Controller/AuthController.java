package br.com.futuroDev.APISustentavel.Controller;

import br.com.futuroDev.APISustentavel.Configuration.JwtUtil;
import br.com.futuroDev.APISustentavel.Model.Dtos.AuthRequest;
import br.com.futuroDev.APISustentavel.Model.Dtos.AuthResponse;
import br.com.futuroDev.APISustentavel.Service.UserDetailsServiceImpl;
import br.com.futuroDev.APISustentavel.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ModelMapper modelMapper;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

            if (userService.validatePassword(authRequest.getPassword(), userDetails.getPassword())){
                String token = jwtUtil.generateToken(userDetails);

                AuthResponse authResponse = modelMapper.map(userDetails, AuthResponse.class);
                authResponse.setToken(token);

                return ResponseEntity.ok(authResponse);

            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
            }

        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }
}
