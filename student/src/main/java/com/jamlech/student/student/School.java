package com.jamlech.student.student;

import lombok.Builder;

@Builder
public record School(
     int id,
     String schoolName,
     String location,
     String principalName
) {
}

