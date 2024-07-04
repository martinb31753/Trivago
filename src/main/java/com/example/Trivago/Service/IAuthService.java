package com.example.Trivago.Service;

import com.example.Trivago.DTO.Security.AuthResponseDto;
import com.example.Trivago.DTO.Security.LoginRequestDto;
import com.example.Trivago.DTO.Security.RegisterRequestDto;

public interface IAuthService {
    AuthResponseDto login(LoginRequestDto userDto);
    AuthResponseDto register(RegisterRequestDto userToRegisterDto);
}
