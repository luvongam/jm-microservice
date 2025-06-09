package com.jamlech.demo1.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Integration tests for UserController
 * Tests the full application context with proper data setup
 */
@SpringBootTest
//@ActiveProfiles("test")
//@TestPropertySource(locations = "classpath:application.yaml")
@Transactional // Rollback transactions after each test
class UserControllerTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        // Create test data
        testUser = new User();
        testUser.setId(1L);
        testUser.setName("tester");

    }

    @Test
    @DisplayName("Should find user by valid ID")
    void testFindUserById_ValidId_ReturnsUser() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(testUser));

        // Act
        User foundUser = userService.findUserById(userId);

        // Assert
        assertNotNull(foundUser, "User should not be null");
        assertEquals(userId, foundUser.getId(), "User ID should match");
        assertEquals("tester", foundUser.getName(), "Username should match");


        // Verify repository interaction
        verify(userRepository, times(1)).findById(userId);
    }


}


@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        // Create test data
        testUser = new User();
        testUser.setId(1L);
        testUser.setName("tester");

    }

    @Test
    @DisplayName("Unit Test: Should find user by ID")
    void findUserById_Success() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(testUser));

        // Act
        User result = userService.findUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("tester", result.getName());
        verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("Unit Test: Should handle user not found")
    void findUserById_NotFound() {
        // Arrange
        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.findUserById(1L);
        });
        verify(userRepository).findById(1L);
    }
}


