import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css'],
  providers: [BookService],
})
export class ListComponent implements OnInit {
  constructor(private activatedRoute: ActivatedRoute, private router: Router) {}

  hasPrevious: boolean = false;
  hasNext: boolean = false;
  character!: string;

  ngOnInit(): void {
    this.character = this.activatedRoute.snapshot.params['character'];
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
