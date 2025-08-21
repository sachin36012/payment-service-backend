package com.payment.controller;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.payment.config.JwtUtil;
import com.payment.dto.AuthRequest;
import com.payment.dto.AuthResponse;
import com.payment.dto.RegisterRequest;
import com.payment.model.User;
import com.payment.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final AuthenticationManager authenticationManager;

	public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody RegisterRequest req) {
	    if (userRepository.findByEmail(req.getEmail()).isPresent()) {
	        return ResponseEntity.badRequest().body("Email already in use");
	    }

	    User u = new User();
	    u.setEmail(req.getEmail());
	    u.setName(req.getName());
	    u.setPassword(passwordEncoder.encode(req.getPassword()));
	    u.setPhNo(req.getPhNo());
	    u.setPin(req.getPin());
	    u.setUuid(UUID.randomUUID());
	    u.setRole(req.getRole()); // 👈 Assign role from request
	    userRepository.save(u);

	    return ResponseEntity.ok("User registered");
	}


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest req) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
		User user = userRepository.findByEmail(req.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));
		String token = jwtUtil.generateToken(user);
		return ResponseEntity.ok(new AuthResponse(token));
	}

}
