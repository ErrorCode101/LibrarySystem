import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Book.ManageComponent } from './book.manage.component';

describe('Book.ManageComponent', () => {
  let component: Book.ManageComponent;
  let fixture: ComponentFixture<Book.ManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Book.ManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Book.ManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
