import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { Teacher } from "../../models/Teacher";

@Component({
    selector: 'app-teachersample',
    standalone: true,
    templateUrl: './teachersample.component.html',
    styleUrls: ['./teachersample.component.scss'],
    imports: [CommonModule]
})
export class TeacherSampleComponent  {
    teacher: Teacher = new Teacher(
        1,
        'Jane Smith',
        '9876543210',
        'jane@example.com',
        'English',
        15
    );

    logTeacherAttributes() {
        this.teacher.logAttributes();
    }
  
}
