import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EspecialidadesMedico } from '../../../entidad/EspecialidadesMedico';

@Injectable({
  providedIn: 'root'
})
export class EspecialidadService {

  @Output()
  public emiter: EventEmitter<EspecialidadesMedico> = new EventEmitter();

  constructor(private httpClient: HttpClient) { }

}
