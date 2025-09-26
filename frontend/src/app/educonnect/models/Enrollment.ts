import { Course } from "./Course";
import { Student } from "./Student";

export class Enrollment {
    enrollmentId: number;
    student: Student;
    course: Course;
    enrollmentDate: Date;

    constructor(
        enrollmentId: number,
        student: Student,
        course: Course,
        enrollmentDate: Date
    ) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }
}

