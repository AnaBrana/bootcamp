import { Component } from '@angular/core';


@Component({
  selector: 'app-calculadora',
  standalone: true,
  imports: [],
  templateUrl: './calculadora.component.html',
  styleUrl: './calculadora.component.css'
})
export class CalculadoraComponent {
 numero1: number=0;
 numero2:number=0;
 resultado:number=0;

  sumar(): void {
    this.mostrarResultado(this.numero1 + this.numero2);
}

 restar(): void {
  this.mostrarResultado(this.numero1 - this.numero2);
}

 multiplicar(): void { 
  this.mostrarResultado(this.numero1 * this.numero2);
}

 dividir(): void {
    if(this.numero1!=0){
      this.mostrarResultado(this.numero1 / this.numero2);
    }
   
}
 mostrarResultado(resultado: number): void {
   switch(resultado){
    case 1:
      this.sumar();
    break;
    case 2: 
      this.restar();
    break;
    case 3:
      this.multiplicar();
    break;
    case 4:
      this.dividir();
    break;
   }
}
}

