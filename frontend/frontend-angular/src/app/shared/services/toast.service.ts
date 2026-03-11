import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export type ToastType = 'success' | 'error';

export interface ToastMessage {
  type: ToastType;
  text: string;
}

@Injectable({ providedIn: 'root' })
export class ToastService {
  readonly message$ = new BehaviorSubject<ToastMessage | null>(null);

  success(text: string): void { this.message$.next({ type: 'success', text }); }
  error(text: string): void { this.message$.next({ type: 'error', text }); }
  clear(): void { this.message$.next(null); }
}