import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreeMenuComponent } from './tree-menu.component';

describe('TreeComponent', () => {
  let component: TreeMenuComponent;
  let fixture: ComponentFixture<TreeMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TreeMenuComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TreeMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
