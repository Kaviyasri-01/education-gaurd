
package eduguard.demo.controller;

import eduguard.demo.entity.Student;
import eduguard.demo.service.StudentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String showStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("student", new Student());
        return "students";
    }

    @PostMapping("/students")
    public String addStudent(
            @ModelAttribute("student") Student student,
            RedirectAttributes redirectAttributes) {
        try {
            studentService.addStudent(student);
            redirectAttributes.addFlashAttribute(
                    "success", "Student added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "error", "Could not add student. Check the email is unique.");
        }
        return "redirect:/students";
    }

    @PostMapping("/students/{id}/delete")
    public String deleteStudent(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute(
                    "success", "Student deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "error", "Could not delete student.");
        }
        return "redirect:/students";
    }
}
