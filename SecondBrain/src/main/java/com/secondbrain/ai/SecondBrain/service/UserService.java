package com.secondbrain.ai.SecondBrain.service;

import com.secondbrain.ai.SecondBrain.dto.user.AuthResponse;
import com.secondbrain.ai.SecondBrain.dto.user.LoginRequest;
import com.secondbrain.ai.SecondBrain.dto.user.RegisterRequest;
import com.secondbrain.ai.SecondBrain.dto.user.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    UserResponse findById(Long id);

    List<UserResponse> findAll();

    UserResponse update(Long id, RegisterRequest request);

    void delete(Long id);
}
