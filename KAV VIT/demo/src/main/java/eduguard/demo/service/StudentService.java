
package eduguard.demo.service;

import eduguard.demo.entity.Student;
import eduguard.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student addStudent(Student student) {
        double attendance = student.getAttendancePercentage();
        double marks = student.getMarks();

        if (attendance < 60 || marks < 40) {
            student.setRiskLevel("HIGH");
        } else if (attendance < 75 || marks < 60) {
            student.setRiskLevel("MEDIUM");
        } else {
            student.setRiskLevel("LOW");
        }

        return repository.save(student);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}
