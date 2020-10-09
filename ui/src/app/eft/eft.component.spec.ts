import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EftComponent } from './eft.component';

describe('EftComponent', () => {
  let component: EftComponent;
  let fixture: ComponentFixture<EftComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EftComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
