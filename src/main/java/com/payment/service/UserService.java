package com.payment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.payment.dto.CardDto;
import com.payment.dto.UserDto;
import com.payment.model.User;
import com.payment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
    	log.info("Request Receved to fetch all users from DB");
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }
        User existingUser = optionalUser.get();
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhNo(updatedUser.getPhNo());
        existingUser.setPin(updatedUser.getPin());
        return userRepository.save(existingUser);
    }

    public String deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.deleteById(user.getId());
        return "User Deleted Successfully";
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUuid(user.getUuid());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPin(user.getPin());
        dto.setPhNo(user.getPhNo());
        if (user.getCards() != null) {
            dto.setCards(user.getCards().stream().map(c -> {
                CardDto cd = new CardDto();
                cd.setId(c.getId());
                cd.setName(c.getName());
                cd.setLastFourDigits(c.getLastFourDigits());
                cd.setExpiry(c.getExpiry());
                return cd;
            }).collect(Collectors.toList()));
        }
        return dto;
    }

	public User findByEmail(String email) {
		return userRepository.findByEmail(email).get();
	}
}
