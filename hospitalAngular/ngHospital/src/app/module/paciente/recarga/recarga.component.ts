import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Recarga } from '../../../../entidad/Recarga';
import { Usuario } from '../../../../entidad/Usuario';
import { HistorialRecargaService } from '../../../service/reports/report-paciente/historial-recarga.service';

@Component({
  selector: 'app-recarga',
  templateUrl: './recarga.component.html',
  styleUrls: ['./recarga.component.css']
})
export class RecargaComponent implements OnInit {

  form = new FormGroup({
    cantidad: new FormControl()
  });
  recarga: Recarga;
  user: Usuario;
  constructor(private serviceRecarga: HistorialRecargaService) {
    this.recarga = new Recarga();
    let jsonUser = localStorage.getItem('userLogin');
    this.user = jsonUser ? JSON.parse(jsonUser) : null;
  }

  ngOnInit(): void {
  }

  realizarRecarga() {
    this.recarga.idPaciente = this.user.id;
    this.recarga.monto = this.form.value.cantidad;
    console.log(this.recarga);
    if (this.form.value.cantidad > 0) {
      if (this.recarga.monto > 0) {
        this.serviceRecarga.insert(this.recarga).subscribe(
          response => {
            alert('Se ha hecho la recarga');
            location.reload();
          }, error => {
            alert('No se pudo realizar la recarga.')
          }
        );
      } else {
        alert('El monto deber ser mayor que 0.')
      }
    }

  }

}
