import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicoExamensRevisionComponent } from './medico-examens-revision.component';

describe('MedicoExamensRevisionComponent', () => {
  let component: MedicoExamensRevisionComponent;
  let fixture: ComponentFixture<MedicoExamensRevisionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicoExamensRevisionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicoExamensRevisionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
