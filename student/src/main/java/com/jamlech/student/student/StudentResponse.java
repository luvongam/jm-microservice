package com.jamlech.student.student;

import lombok.Builder;

@Builder
public record StudentResponse(
        String  id,
        String name,
        Integer age,
        School school,
        String gender) {
}
