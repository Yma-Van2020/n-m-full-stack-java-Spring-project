package com.kateyn.myBeltExam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kateyn.myBeltExam.models.Course;
import com.kateyn.myBeltExam.models.Student;
import com.kateyn.myBeltExam.repos.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private CourseService cServ;
	
	@Autowired 
	private StudentRepository sRepo;
	

	public Student save(Student p) {
		return sRepo.save(p);
	}
	
	
	public List<Student> getAll(){
		return sRepo.findAll();
	}
	
	public Student getOne(Long id) {
		Optional<Student> p = sRepo.findById(id);
		if(p.isPresent()) {
			return p.get();
		} else {	
			return null;
		}
	}
	
	
	public Student addToCourse(Long studentId, Long courseId) {
		Student student = getOne(studentId);
		Course course = cServ.getOne(courseId);
		
		student.getCourses().add(course);
		return save(student);
	}
}
