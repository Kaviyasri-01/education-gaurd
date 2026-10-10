
package eduguard.demo;

import eduguard.demo.entity.Student;
import eduguard.demo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final StudentService studentService;

    public DashboardController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {

        List<Student> students = studentService.getAllStudents();

        long highRisk = students.stream()
                .filter(s -> "HIGH".equalsIgnoreCase(s.getRiskLevel()))
                .count();

        long mediumRisk = students.stream()
                .filter(s -> "MEDIUM".equalsIgnoreCase(s.getRiskLevel()))
                .count();

        long lowRisk = students.stream()
                .filter(s -> "LOW".equalsIgnoreCase(s.getRiskLevel()))
                .count();

        model.addAttribute("totalStudents", students.size());
        model.addAttribute("highRisk", highRisk);
        model.addAttribute("mediumRisk", mediumRisk);
        model.addAttribute("lowRisk", lowRisk);

        return "index";
    }
}
