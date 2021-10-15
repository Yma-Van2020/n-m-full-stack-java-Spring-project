package com.kateyn.myBeltExam.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kateyn.myBeltExam.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{
	List<Course>findAll();
	
//	List<Course> findAllByCourses(Course course);
//	
//	 List<Course> findByCourseNotContains(Course course);
}
