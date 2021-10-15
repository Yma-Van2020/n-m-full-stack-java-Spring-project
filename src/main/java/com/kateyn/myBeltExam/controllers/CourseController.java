package com.kateyn.myBeltExam.controllers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kateyn.myBeltExam.models.Course;
import com.kateyn.myBeltExam.models.Student;
import com.kateyn.myBeltExam.models.User;
import com.kateyn.myBeltExam.services.CourseService;
import com.kateyn.myBeltExam.services.StudentService;
import com.kateyn.myBeltExam.services.UserService;

@Controller
public class CourseController {

	@Autowired 
	CourseService courseServ;
	
	@Autowired 
	UserService userServ;
	
	@Autowired
	StudentService sServ;
	
	// ========== Display =======================
	@GetMapping("/courses/new")
	public String newcourse(@ModelAttribute("course") Course course, HttpSession session, Model model) {
		Long userid = (Long) session.getAttribute("user_id");
    	if (userid == null) {
    		return "redirect:/";
    	}
//    	LocalTime time = LocalTime.now();
    	
		User instructor = userServ.getUserById(userid);
		model.addAttribute("instructor", instructor);
//		model.addAttribute("localtime", time);
		
			return "create.jsp";
	}
	
	@GetMapping("/courses/{id}")
	public String showcourse(@ModelAttribute("student") Student student, @PathVariable("id") Long id, 
			Model model, HttpSession session) {
		Long userid = (Long) session.getAttribute("user_id");
    	if (userid == null) {
    		return "redirect:/";
    	}
    	
    	Course course = courseServ.getOne(id);
    	
    	model.addAttribute("allStudents", sServ.getAll());
    	model.addAttribute("course", course);
    	return "display.jsp";
	}
	
	
	@RequestMapping("/courses/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userid = (Long) session.getAttribute("user_id");
    	if (userid == null) {
    		return "redirect:/";
    	}
    	
		Course course = courseServ.getOne(id);
		model.addAttribute("course", course);
		return "edit.jsp";
	}
	
	@PutMapping("/courses/{id}/update")
	public String update(@Valid @ModelAttribute("course") Course course, BindingResult result, @PathVariable("id") Long id, HttpSession session, Model model) {
		Long userid = (Long) session.getAttribute("user_id");
    	if (userid == null) {
    		return "redirect:/";
    	}
    	
		if(result.hasErrors()) {
		
			return "edit.jsp";
		} else {
			Course courseFromDB = courseServ.getOne(id);
			
			courseFromDB.setName(course.getName());
			courseFromDB.setDayOfWeek(course.getDayOfWeek());
			courseFromDB.setDescription(course.getDescription());
			
			courseServ.updateCourse(course);
			return "redirect:/home";
		}
	}
	
    @DeleteMapping("/courses/{id}/delete")
    public String destroy(@PathVariable("id") Long id, HttpSession session) {
    	Long userid = (Long) session.getAttribute("user_id");
    	if (userid == null) {
    		return "redirect:/";
    	}
        courseServ.delete(id);
        return "redirect:/home";
    }
    
	
	@PostMapping("/courses/create")
	public String createcourse(@Valid @ModelAttribute("course") Course course, BindingResult result, HttpSession session) {
		Long userid = (Long) session.getAttribute("user_id");
    	if (userid == null) {
    		return "redirect:/";
    	}
    	System.out.println(result);
    	if (result.hasErrors()) {
    		System.out.println("there is error");
    		return "create.jsp";
    	} 
    	
    		courseServ.saveCourse(course);
    		
    		return "redirect:/home";
    	}

	@PostMapping("/courses/{courseId}/addStudent")
	public String addStudent(@PathVariable("courseId") Long courseId, 
			@RequestParam("studentId") Long studentId) {
		System.out.println("this is courseid: " + courseId);
		courseServ.addToStudent(courseId, studentId);
		return "redirect:/courses/" + courseId;
	}
    	
	}

	
