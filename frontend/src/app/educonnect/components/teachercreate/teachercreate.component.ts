import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Teacher } from '../../models/Teacher';
import { EduConnectService } from '../../services/educonnect.service';

@Component({
  selector: 'app-teachercreate',
  templateUrl: './teachercreate.component.html',
  styleUrls: ['./teachercreate.component.scss']
})
export class TeacherCreateComponent implements OnInit {
  teacherForm: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private formBuilder: FormBuilder, private educonnectService: EduConnectService) { }

  ngOnInit(): void {
    this.teacherForm = this.formBuilder.group({
      teacherId: [null],
      fullName: ['', [Validators.required, Validators.minLength(2)]],
      subject: ['', [Validators.required]],
      contactNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      email: ['', [Validators.required, Validators.email]],
      yearsOfExperience: [null, [Validators.required, Validators.min(1)]]
    });
  }

  onSubmit(): void {
    if (this.teacherForm.valid) {
      this.educonnectService.addTeacher(this.teacherForm.value).subscribe({
        next: (response) => {
          this.errorMessage = null;
          console.log(response);
          this.teacherForm.reset();
        },
        error: (error) => {
          this.handleError(error);
        },
        complete: () => {
          this.successMessage = 'Teacher created successfully!';
        }
      });
    }
  }

  private handleError(error: HttpErrorResponse): void {
    if (error.error instanceof ErrorEvent) {
    
      this.errorMessage = ` ${error.error.message}`;
    } else {
      
      this.errorMessage = `${error.error}`;
    }
    this.successMessage = null;
  }

}
