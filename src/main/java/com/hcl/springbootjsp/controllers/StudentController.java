package com.hcl.springbootjsp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.hcl.springbootjsp.model.Student;

@Controller
@RequestMapping("/student")
public class StudentController {
	//I don't think the below is needed, but its working for now.
	private List<Student> initData = new ArrayList<>();

	@PersistenceContext
	private EntityManager entityManager;
	
	public StudentController() {

	}

	//To render the form for the student
    @GetMapping("/addStudent")
    public String addStudentView(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    //This one was called when you POST from a form tag
    @Transactional
    @PostMapping("/addStudent")
    public RedirectView addStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/student/addStudent", true);
        
        entityManager.persist(student);
        
        redirectAttributes.addFlashAttribute("savedStudent", student);
        redirectAttributes.addFlashAttribute("addStudentSuccess", true);
        return redirectView;
    }
	
	@GetMapping("/viewStudents")
    public String viewStudents(Model model) {
       Query ReadAll = entityManager.createQuery("select s from Student s");
       List<Student> resultListAll = ReadAll.getResultList();
       resultListAll.forEach(System.out::println);
       model.addAttribute("students", resultListAll);
       return"view-students";
    }
	@DeleteMapping("deleteStudents")
	public void deleteStudent(@PathVariable int id) {
		Query delete = entityManager.createQuery("delete from student s where s.id=?");
		delete.setParameter(0, id);
		delete.executeUpdate();
	}
}