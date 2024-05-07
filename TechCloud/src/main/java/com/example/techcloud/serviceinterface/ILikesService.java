package com.example.techcloud.serviceinterface;

import com.example.techcloud.entity.Evenement;
import com.example.techcloud.entity.Likes;

import java.util.List;

public interface ILikesService {
    public void addLike(Evenement event);
    public List<Likes> getLikesByEvent(Evenement event);
}
