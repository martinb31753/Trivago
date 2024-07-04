package com.example.Trivago.Service;

import com.example.Trivago.DTO.Security.AuthResponseDto;
import com.example.Trivago.DTO.Security.LoginRequestDto;
import com.example.Trivago.DTO.Security.RegisterRequestDto;
import com.example.Trivago.Entity.Rol;
import com.example.Trivago.Entity.UserEntity;
import com.example.Trivago.Repository.IUserRepository;
import com.example.Trivago.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServImpl implements IAuthService{
    private final IUserRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponseDto login(LoginRequestDto userDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword()));
        UserDetails user = repository.findByUsername(userDto.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponseDto.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponseDto register(RegisterRequestDto userToRegisterDto) {
        UserEntity user = UserEntity.builder()
                .username(userToRegisterDto.getUsername())
                .password(passwordEncoder.encode(userToRegisterDto.getPassword()))
                .firstName(userToRegisterDto.getFirstName())
                .lastName(userToRegisterDto.getLastName())
                .role(Rol.USER)
                .build();

        repository.save(user);

        return AuthResponseDto.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
