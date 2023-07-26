package com.safalifter.jobservice.repository;

import com.safalifter.jobservice.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, String> {
    List<Offer> getOffersByUser_Id(String id);

    List<Offer> getOffersByAdvert_Id(String id);
}
