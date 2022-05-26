package com.developer.rest.mongo.controller;

import com.developer.rest.mongo.entity.Student;
import com.developer.rest.mongo.repository.StudentRepository;
import com.developer.rest.mongo.servicesImp.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @GetMapping("findAll")
    public ResponseEntity  getAllStudents(){

        return  ResponseEntity.ok(studentRepository.findAll());
    }


    @PostMapping("create")
    public  ResponseEntity createStudent(@RequestBody Student student){


        try {
            student.setId(sequenceGeneratorService.generateSequence(Student.SEQUENCE_NAME));
            student.setCreatedAt(new Date());
            studentRepository.save(student);
           return  ResponseEntity.ok("Student Created Successfully");
        }catch (Exception ex){
            return  new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @PutMapping("update")
    public  ResponseEntity updateStudent(@RequestBody Student student){

        try {


            Student stdFromDb=studentRepository.findById(student.getId()).orElse(null);
     if (stdFromDb!=null){
              stdFromDb.setUpdateAt(new Date());
              stdFromDb.setName(student.getName());
              stdFromDb.setRollNumber(student.getRollNumber());
              studentRepository.save(stdFromDb);
              return  ResponseEntity.ok("Student Created Successfully");
          }else{
              return  new ResponseEntity("Id Not Found",HttpStatus.NOT_FOUND);
          }
        }catch (Exception ex){
            return  new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }




}
