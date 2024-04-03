package com.pluralsight.courseinfo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    @Test
    void validateDoesNotThrow() {
        assertDoesNotThrow(() -> new Course("id", "name", 0, "url"), "Did throw.");
    }

    @Test
    void validateThrowsOnId() {
        assertThrows(IllegalArgumentException.class, () -> new Course("", "name", 0, "url"), "Did not throw.");
    }

    @Test
    void validateThrowsOnName() {
        assertThrows(IllegalArgumentException.class, () -> new Course("id", "", 0, "url"), "Did not throw.");
    }

    @Test
    void validateThrowsOnUrl() {
        assertThrows(IllegalArgumentException.class, () -> new Course("", "name", 0, ""), "Did not throw.");
    }
}