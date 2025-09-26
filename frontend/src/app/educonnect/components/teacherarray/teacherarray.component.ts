import { Component } from '@angular/core';
import { Teacher } from '../../models/Teacher';

@Component({
    selector: 'app-teacherarray',
    templateUrl: './teacherarray.component.html',
    styleUrls: ['./teacherarray.component.scss']
})
export class TeacherArrayComponent {
    showDetails: boolean = false;

    teachers: Teacher[] = [
        new Teacher(1, 'Jane Smith', '9876543210', 'jane.smith@example.com', 'Mathematics', 10),
        new Teacher(2, 'John Doe', '1234567890', 'john.doe@example.com', 'Science', 8),
        new Teacher(3, 'Alice Johnson', '4567891230', 'alice.johnson@example.com', 'History', 12),
    ];

    constructor() { }

    toggleDetails(): void {
        this.showDetails = !this.showDetails;
    }
}
