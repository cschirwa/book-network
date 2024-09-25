package com.redfintek.booknetworkapi;

import com.redfintek.booknetworkapi.model.Author;
import com.redfintek.booknetworkapi.model.Book;
import com.redfintek.booknetworkapi.model.Role;
import com.redfintek.booknetworkapi.model.User;
import com.redfintek.booknetworkapi.repositories.BookRepository;
import com.redfintek.booknetworkapi.repositories.RoleRepository;
import com.redfintek.booknetworkapi.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class BootstrapData {

    private final RoleRepository roleRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public BootstrapData(RoleRepository roleRepository, BookRepository bookRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        loadRoles();
        loadUsers();
        loadBooks();
    }

    private void loadBooks() {
        var atomicHabits = Book.builder()
                .title("Atomic Habits")
                .authors(Arrays.asList(new Author(null, "James", "Clear", null)))
                .isbn("1234567ASDFG")
                .build();
        bookRepository.save(atomicHabits);
        log.info("Book : " + atomicHabits.getTitle() + " Saved");

        var cantmakethisup = Book.builder()
                .title("I Can't Make This Up")
                .authors(Arrays.asList(new Author(null, "Kevin", "Hart", null)))
                .isbn("78SY9DAHSHPAS9DAS")
                .build();
        bookRepository.save(cantmakethisup);
        log.info("Book : " + cantmakethisup.getTitle() + " Saved");
    }

    private void loadUsers() {
        var calvin = User.builder()
                .firstname("Calvin")
                .lastname("Chirwa")
                .username("calvin")
                .password(passwordEncoder.encode("123123"))
                .roles(Arrays.asList(roleRepository.findByName("ROLE_USER")
                        .orElseThrow(() -> new EntityNotFoundException("Role not found"))))
                .isAccountNonExpired(true)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .build();
        userRepository.save(calvin);
        log.info("User " + calvin.getName() + " saved");
    }

    private void loadRoles() {
        Role user = new Role();
        user.setName("ROLE_USER");
        roleRepository.save(user);
        log.info("User Role loaded");

        Role admin = new Role();
        admin.setName("ROLE_ADMIN");
        roleRepository.save(admin);
        log.info("Admin Role loaded");
    }
}
