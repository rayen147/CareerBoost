package com.example.techcloud.serviceimpl;

import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.entity.Evenement;
import com.example.techcloud.entity.Likes;
import com.example.techcloud.repository.ILikesRepository;
import com.example.techcloud.serviceinterface.ILikesService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LikesService implements ILikesService {
    ILikesRepository likeRepository;
    @Override
    public void addLike(Evenement event) {


        Likes likes = new Likes();

        likes.setEvent(event);
        likeRepository.save(likes);

    }

    @Override
    public List<Likes> getLikesByEvent(Evenement event) {
        return likeRepository.findAllByEvent(event);
    }
}
