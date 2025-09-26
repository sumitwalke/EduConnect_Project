import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EduConnectService } from '../../services/educonnect.service';

@Component({
  selector: 'app-studentcreate',
  templateUrl: './studentcreate.component.html',
  styleUrls: ['./studentcreate.component.scss']
})
export class StudentCreateComponent implements OnInit {
  studentForm!: FormGroup; 
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private formBuilder: FormBuilder, private educonnectService: EduConnectService) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.studentForm = this.formBuilder.group({
      studentId: [null],
      fullName: ['', [Validators.required, Validators.minLength(2)]],
      dateOfBirth: ['', [Validators.required]],
      contactNumber: [
        '',
        [Validators.required, Validators.pattern('^[0-9]{10}$')]
      ],
      email: ['', [Validators.required, Validators.email]],
      address: ['', [Validators.required, Validators.minLength(5)]]
    });
  }

  onSubmit(): void {
    if (this.studentForm.valid) {
      this.educonnectService.addStudent(this.studentForm.value).subscribe({
        next: (response) => {
          this.errorMessage = null;
          console.log(response);
          this.studentForm.reset();
        },
        error: (error) => {
          this.handleError(error);
        },
        complete: () => {
          this.successMessage = 'Student created successfully!';
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

