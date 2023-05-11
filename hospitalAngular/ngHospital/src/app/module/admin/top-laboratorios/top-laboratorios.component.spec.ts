import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopLaboratoriosComponent } from './top-laboratorios.component';

describe('TopLaboratoriosComponent', () => {
  let component: TopLaboratoriosComponent;
  let fixture: ComponentFixture<TopLaboratoriosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TopLaboratoriosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TopLaboratoriosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
