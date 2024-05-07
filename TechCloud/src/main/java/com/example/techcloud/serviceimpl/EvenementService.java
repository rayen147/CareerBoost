package com.example.techcloud.serviceimpl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.entity.Evenement;
import com.example.techcloud.repository.ICommentaireRepository;
import com.example.techcloud.repository.IEvenementRepository;
import com.example.techcloud.serviceinterface.IEvenementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class  EvenementService implements IEvenementService {
    IEvenementRepository iEvenementRepository;
    ICommentaireRepository iCommentaireRepository;

    private static final String
            containerName = "wadhah";
    // Définir la chaîne de connexion
    private static final String connectionString = "DefaultEndpointsProtocol=https;AccountName=wadhahdaoud;AccountKey=axmwySmbufN/2Z6vfigEXhcvCih9gf2rp4YNSpJTRfpsMKyY4OLZGuMPanDfSV8U4deBziC2AaIN+AStYZYACw==;EndpointSuffix=core.windows.net";
    @Override
    public Evenement addEvenement(Evenement evenement , MultipartFile file) {
        try {
            String image=uploadToAzureBlobStorage(file);
            evenement.setImage(image);
        } catch (IOException e)  {
            throw new RuntimeException("ERREUR");
        }

        return iEvenementRepository.save(evenement);
    }

    @Override
    public List<Evenement> getAllEvenement() {
        return iEvenementRepository.findAll();
    }

    @Override
    public Evenement getEvenementById(long id) {
        return iEvenementRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEvenement(long id) {
        iEvenementRepository.deleteById(id);

    }

    @Override
    public Evenement updateEvenement(Evenement evenement) {
        return iEvenementRepository.save(evenement);
    }


    public void ajouterCommentaire(Long evenementId, String contenuCommentaire) {
        Evenement evenement = iEvenementRepository.findById(evenementId).orElseThrow();
        Commentaire commentaire = new Commentaire();
        commentaire.setDescription(contenuCommentaire);
        commentaire.setEvenement(evenement);
        evenement.getCommentaire().add(commentaire);
        iEvenementRepository.save(evenement);
    }
    public List<Commentaire> getCommentairesByEvenementId(Long evenementId) {
        Evenement evenement = iEvenementRepository.findById(evenementId).orElse(null);
        return evenement.getCommentaire();
    }

    public void updateCommentaire(Long evenementId, Long commentaireId, String nouvelleDescription) {
        Evenement evenement = iEvenementRepository.findById(evenementId).orElseThrow();
        Commentaire commentaire = iCommentaireRepository.findById(commentaireId).orElseThrow();
        commentaire.setEvenement(evenement);
        commentaire.setDescription(nouvelleDescription); // Mettre à jour la description
        iCommentaireRepository.save(commentaire);
    }

    public void supprimerCommentaire(Long evenementId, Long commentaireId) {
        Evenement evenement = iEvenementRepository.findById(evenementId).orElseThrow();
        Commentaire commentaire = iCommentaireRepository.findById(commentaireId).orElseThrow();

        evenement.getCommentaire().remove(commentaire); // Supprimer le commentaire de la liste
        iCommentaireRepository.delete(commentaire); // Supprimer le commentaire de la base de données
        iEvenementRepository.save(evenement); // Enregistrer les modifications
    }

    private String uploadToAzureBlobStorage(MultipartFile file) throws IOException {

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();

        System.out.println("connection reussite");
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        System.out.println("container found");
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // Uploader le fichier dans le conteneur Azure Blob Storage
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        blobClient.upload(file.getInputStream(), file.getSize());

        // Retournez l'URL de l'image dans Azure Blob Storage
        return blobClient.getBlobUrl();
    }






}
