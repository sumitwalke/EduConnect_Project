import { Teacher } from "./Teacher";

export class Course {
    courseId: number;
    courseName: string;
    description: string;
    teacher: Teacher;

    constructor(
        courseId: number,
        courseName: string,
        description: string,
        teacher: Teacher
    ) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.teacher = teacher;
    }

}