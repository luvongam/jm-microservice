package com.jamlech.student.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final RestTemplate restTemplate;
    private final StudentRepository studentRepository;

    public ResponseEntity<?> createStudent(Student student){
        try{
            return new ResponseEntity<Student>(studentRepository.save(student), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> fetchStudentById(String id){
        Optional<Student> student =  studentRepository.findById(id);
        if(student.isPresent()){
//            School school = restTemplate.getForObject("http://localhost:8085/schools/" + student.get().getSchoolId(), School.class);
            School school = restTemplate.getForObject("http://SCHOOL-SERVICE/schools/"+student.get().getSchoolId(), School.class);
            StudentResponse studentResponse =StudentResponse.builder()
                            .id(student.get().getId())
                            .name(student.get().getName())
                            .age(student.get().getAge())
                            .school(school)
                            .gender(student.get().getGender())
                            .build();

            return new ResponseEntity<>(studentResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Student Found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> fetchStudents(){
        List<Student> students = studentRepository.findAll();
        if(!students.isEmpty()){
            return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No Students",HttpStatus.NOT_FOUND);
        }
    }



}
