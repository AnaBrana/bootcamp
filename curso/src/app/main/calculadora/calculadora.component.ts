import { Component } from '@angular/core';


@Component({
  selector: 'app-calculadora',
  standalone: true,
  imports: [],
  templateUrl: './calculadora.component.html',
  styleUrl: './calculadora.component.css'
})
export class CalculadoraComponent {
  primerNumero: number = 0;
  segundoNumero: number = 0;
  resultado: number = 0;

  sumar() {
    this.resultado = this.primerNumero + this.segundoNumero;
  }

  restar() {
    this.resultado = this.primerNumero - this.segundoNumero;
  }

  multiplicar() {
    this.resultado = this.primerNumero * this.segundoNumero;
  }

  dividir() {
    this.resultado = this.primerNumero / this.segundoNumero;
  }
  
}

