import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerFile?accion=";
  constructor(private httpClient: HttpClient) { }

  public enviarArchivoPDF(form: FormData, folder: string, nombreExamen: string, idExamen: number, idSolicitud: number, username: string) {
    return this.httpClient.post(
      this.URL + folder + "&examen=" + nombreExamen + "&solicitud=" + idSolicitud + "&username=" + username + "&idExamen=" + idExamen,
      form,
      { responseType: 'blob' });
  }

  public downloadaPDF(foler: string, path: string) {
    return this.httpClient.get(this.URL + "download&folderName=" + foler + "&path=" + path, { responseType: 'blob' });
  }
}
