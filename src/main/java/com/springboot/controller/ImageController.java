package com.springboot.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    private static final String SAVE_DIRECTORY = "C:\\Users\\lreye\\OneDrive\\Escritorio\\Desarrollo Proyecto Arqui";

    @PostMapping("/guardar-imagen")
    public ResponseEntity<String> guardarImagen(@RequestBody String imagenBase64) {
        try {
            // Decodificar la imagen desde Base64
            byte[] imagenBytes = Base64.getDecoder().decode(imagenBase64);

            // Crear un flujo de salida para guardar la imagen en el directorio deseado
            FileOutputStream outputStream = new FileOutputStream(SAVE_DIRECTORY + "\\descarga.jpg");
            outputStream.write(imagenBytes);
            outputStream.close();

            return ResponseEntity.ok("Imagen guardada con éxito.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al guardar la imagen: " + e.getMessage());
        }
    }
}
