import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { Student } from "../../models/Student";

@Component({
    selector: 'app-studentsample',
    standalone: true,
    templateUrl: './studentsample.component.html',
    styleUrls: ['./studentsample.component.scss'],
    imports: [CommonModule]
})
export class StudentSampleComponent {
    student: Student = new Student(
        1,
        'John Doe',
        new Date('1990-01-01'),
        '1234567890',
        'john@example.com',
        '123 Main Street, Cityville'
    );

    logStudentAttributes() {
        this.student.logAttributes();
    }
}
