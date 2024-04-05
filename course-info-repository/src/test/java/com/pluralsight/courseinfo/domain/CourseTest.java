package com.pluralsight.courseinfo.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    @Test
    void validateDoesNotThrow() {
        assertDoesNotThrow(() -> new Course("id", "name", 0, "url", Optional.empty()), "Did throw.");
    }

    @Test
    void validateThrowsOnId() {
        assertThrows(IllegalArgumentException.class, () -> new Course("", "name", 0, "url", Optional.empty()), "Did not throw.");
    }

    @Test
    void validateThrowsOnName() {
        assertThrows(IllegalArgumentException.class, () -> new Course("id", "", 0, "url", Optional.empty()), "Did not throw.");
    }

    @Test
    void validateThrowsOnUrl() {
        assertThrows(IllegalArgumentException.class, () -> new Course("id", "name", 0, "", Optional.empty()), "Did not throw.");
    }

    @Test
    void validateThrowsOnNotes() {
        assertThrows(IllegalArgumentException.class, () -> new Course("id", "name", 0, "url", Optional.of("")), "Did not throw.");
    }

    @Test
    void validateThrowsOnNull() {
        assertThrows(IllegalArgumentException.class, () -> new Course(null, null, 0, null, Optional.empty()), "Did not throw.");
    }
}