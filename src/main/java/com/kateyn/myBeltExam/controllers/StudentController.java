package com.kateyn.myBeltExam.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kateyn.myBeltExam.models.Course;
import com.kateyn.myBeltExam.models.Student;
import com.kateyn.myBeltExam.services.CourseService;
import com.kateyn.myBeltExam.services.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService pServ;
	
	@Autowired
	private CourseService cServ;
	

	
	// ========== Action ========================
	@PostMapping("/students/create")
	public String createStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "display.jsp";
		} else {
			pServ.save(student);
			return "display.jsp";
		}
	}
	
	@PostMapping("/students/{studentId}/addCourse")
	public String addcourse(@PathVariable("studentId") Long studentId, @RequestParam("courseId") Long courseId) {
		pServ.addToCourse(studentId, courseId);
		return "display.jsp";
	}
		
}	

