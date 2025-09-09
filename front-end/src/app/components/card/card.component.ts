import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- importa aqui
import { CommonModule } from '@angular/common'; // boa prática

@Component({
  selector: 'app-card',
  standalone: true,              // <-- standalone
  imports: [CommonModule, FormsModule], // <-- adiciona aqui
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent {
  @Input() width?: string;
  @Input() height?: string;
  @Input() title: string = '';
  @Input() description: string = '';
  @Input() showinput: boolean = false;
  @Input() placeholder: string = '';
  @Input() buttonText?: string = '';
  valueInput: string = '';

  @Output() inputChange = new EventEmitter<string>();
  @Output() buttonClick = new EventEmitter<void>();

  onInputChange() {
    this.inputChange.emit(this.valueInput);
  }

  onButtonClick() {
    this.buttonClick.emit();
  }
}
