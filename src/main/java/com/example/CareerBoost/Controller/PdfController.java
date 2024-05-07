package com.example.CareerBoost.Controller;

import com.itextpdf.io.IOException;
<<<<<<< HEAD
import org.springframework.core.io.FileSystemResource;
=======
import org.springframework.core.io.ClassPathResource;
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;

@RestController
@RequestMapping("/pdfs")
public class PdfController {

<<<<<<< HEAD
  /*  @GetMapping("/{filename:.+}")
=======
    @GetMapping("/{filename:.+}")
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
    public ResponseEntity<byte[]> getPdf(@PathVariable String filename) throws IOException, java.io.IOException {
        Resource resource = new ClassPathResource("pdfs/" + filename);
        byte[] data = Files.readAllBytes(resource.getFile().toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(filename, filename);
        headers.setContentLength(data.length);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
<<<<<<< HEAD
    }*/
  @GetMapping("/{filename:.+}")
  public ResponseEntity<byte[]> getPdf(@PathVariable String filename) throws IOException, java.io.IOException {
      System.out.println("Trying to retrieve PDF: " + filename); // Ajoutez ce log pour vérifier le nom du fichier demandé

      Resource resource = new FileSystemResource("C:/Users/kbaie/IdeaProjects/CareerBoost-master - Copie/src/main/resources/pdfs/" + filename);
      // Vérifiez si la ressource existe
      if (!resource.exists()) {
          System.err.println("PDF file not found: " + filename); // Ajoutez ce log pour signaler que le fichier n'a pas été trouvé
          return ResponseEntity.notFound().build();
      }

      // Lecture du contenu du fichier PDF
      byte[] data = Files.readAllBytes(resource.getFile().toPath());

      // Construction des en-têtes de réponse
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_PDF);
      headers.setContentDispositionFormData(filename, filename);
      headers.setContentLength(data.length);

      return new ResponseEntity<>(data, headers, HttpStatus.OK);
  }

=======
    }
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
}