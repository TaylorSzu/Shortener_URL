import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CardComponent } from './components/card/card.component';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CardComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'shortenerUrl';
  urlInput: string = '';
  shortUrl: string | null = null;
  loading = false;

  constructor(private http: HttpClient) {}

  // Atualiza input e mostra no console
  onInputChange(valor: string) {
    this.urlInput = valor;
    console.log('URL digitada:', this.urlInput);
  }

  // Encurta URL e mostra no console
  onButtonClick() {
    if (!this.urlInput) return;

    this.loading = true;
    this.shortUrl = null;

    console.log('Encurtando URL:', this.urlInput);

    this.http
      .post<{ shortenerUrl: string }>('/url', {
        originalUrl: this.urlInput,
      })

      .subscribe({next: (res) => {
          this.shortUrl = res?.shortenerUrl ?? null;
          console.log('Resposta da API:', res);
          this.loading = false;
          console.log('URL encurtada:', this.shortUrl);
        },
        error: (err) => {
          console.error('Erro ao encurtar URL', err);
          this.loading = false;
        },
      });
  }

  copyToClipboard() {
    if (this.shortUrl) {
      navigator.clipboard.writeText(this.shortUrl).then(() => {
        alert('URL copiada!');
        console.log('URL copiada:', this.shortUrl);
      });
    } else {
      alert('Nenhuma URL para copiar!');
    }
  }
}
