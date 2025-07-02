package com.LPU.FirstProject;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/users")

public class UserController {

    private List<User> users = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();

    // 📌 Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(idCounter.incrementAndGet());
        users.add(user);
        return user;
    }

    // 📌 Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // 📌 Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new RuntimeException("User not found with id: " + id);
    }

    // 📌 Update user by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setName(updatedUser.getName());
                user.setAge(updatedUser.getAge());
                user.setRollNumber(updatedUser.getRollNumber());
                return user;
            }
        }
        throw new RuntimeException("User not found with id: " + id);
    }

    // 📌 Delete user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                users.remove(user);
                return "User deleted with id: " + id;
            }
        }
        throw new RuntimeException("User not found with id: " + id);
    }
}
