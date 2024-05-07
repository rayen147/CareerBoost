package com.example.CareerBoost.ServiceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class PdfService {
<<<<<<< HEAD
    @Value("C:/Users/kbaie/IdeaProjects/CareerBoost-master - Copie/src/main/resources/pdfs/")
    //@Value("classpath:/pdfs/")
    private String pdfStoragePath;

    public void savePdf(byte[] pdfData, String pdfFileName) {
=======
    @Value("C:/Users/kbaie/IdeaProjects/CareerBoost/src/main/resources/pdfs/")
    private String pdfStoragePath;

    private void savePdf(byte[] pdfData, String pdfFileName) {
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
        try {
            String fullPath = pdfStoragePath + pdfFileName; // Chemin complet du fichier PDF
            FileOutputStream fos = new FileOutputStream(fullPath);
            fos.write(pdfData);
            fos.close();
            System.out.println("PDF enregistré avec succès : " + pdfFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}