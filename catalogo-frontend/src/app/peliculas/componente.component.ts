/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable @typescript-eslint/no-empty-function */
/* eslint-disable @angular-eslint/no-empty-lifecycle-method */
import { Component, OnInit, OnDestroy, Input, OnChanges, SimpleChanges, forwardRef } from '@angular/core';
import { ActivatedRoute, Router, ParamMap, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe, NgIf, } from '@angular/common';
import { PaginatorModule } from 'primeng/paginator';
import { ErrorMessagePipe, TypeValidator } from '@my/core';

import { Subscription } from 'rxjs';
import { FilmsViewModelService } from './servicios.service';

@Component({
    selector: 'app-films-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [RouterLink, PaginatorModule]
})
export class FilmsListComponent implements OnChanges, OnDestroy {
 
  constructor(protected vm: FilmsViewModelService) { }
  public get VM(): FilmsViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    this.vm.list()
  }
  ngOnDestroy(): void { this.vm.clear(); }
}
@Component({
    selector: 'app-films-add',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [FormsModule, TypeValidator, ErrorMessagePipe]
})
export class FilmsAddComponent implements OnInit {
  constructor(protected vm: FilmsViewModelService) { }
  public get VM(): FilmsViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.add();
  }
}
@Component({
    selector: 'app-films-edit',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [FormsModule, TypeValidator, ErrorMessagePipe]
})
export class FilmsEditComponent implements OnInit, OnDestroy {
  private obs$?: Subscription;
  constructor(protected vm: FilmsViewModelService,
    protected route: ActivatedRoute, protected router: Router) { }
  public get VM(): FilmsViewModelService { return this.vm; }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe(
      (params: ParamMap) => {
        const id = parseInt(params?.get('id') ?? '');
        if (id) {
          this.vm.edit(id);
        } else {
          this.router.navigate(['/404.html']);
        }
      });
  }
  ngOnDestroy(): void {
    this.obs$!.unsubscribe();
  }
}
@Component({
    selector: 'app-films-view',
    templateUrl: './tmpl-view.component.html',
    styleUrls: ['./componente.component.css'],
    standalone: true,
    imports: [DatePipe]
})
export class FilmsViewComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: FilmsViewModelService, protected router: Router) { }
  public get VM(): FilmsViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}

export const FILMS_COMPONENTES = [
  FilmsListComponent, FilmsAddComponent, FilmsEditComponent, FilmsViewComponent,
];
