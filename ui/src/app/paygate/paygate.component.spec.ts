import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { PaygateComponent } from './paygate.component';

describe('PaygateComponent', () => {
  let component: PaygateComponent;
  let fixture: ComponentFixture<PaygateComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ PaygateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaygateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
