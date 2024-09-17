import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadableComponent } from './loadable.component';

describe('LoadableComponent', () => {
  let component: LoadableComponent;
  let fixture: ComponentFixture<LoadableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoadableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoadableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
