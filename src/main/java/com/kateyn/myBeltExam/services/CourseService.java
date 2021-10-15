package com.kateyn.myBeltExam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kateyn.myBeltExam.models.Course;
import com.kateyn.myBeltExam.models.Student;
import com.kateyn.myBeltExam.repos.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository cRepo;
	
	@Autowired
	private StudentService sServ;
	
	public Course getOne(Long id) {
		Optional<Course> optionalcourse = cRepo.findById(id);
		if (optionalcourse.isPresent()) {
			return optionalcourse.get();
		} else {
			return null;
		}
	}
	
	public Course saveCourse(Course b) {
		return cRepo.save(b);
	}
	
	public List<Course> getAllCourses(){
		return cRepo.findAll();
	}
	
	public Course updateCourse(Course b) {
		return cRepo.save(b);
	}
	
	public void delete(Long id) {
		cRepo.deleteById(id);
	}

	public Course addToStudent(Long courseId,Long studentId) {
		Course course = getOne(courseId);
		Student student = sServ.getOne(studentId);
		
		System.out.println(student);
		System.out.println(course);//null
		course.getStudents().add(student);
		return saveCourse(course);
	}
	
}
