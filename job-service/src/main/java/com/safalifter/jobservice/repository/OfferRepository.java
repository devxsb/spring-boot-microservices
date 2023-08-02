package com.safalifter.jobservice.repository;

import com.safalifter.jobservice.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, String> {
    List<Offer> getOffersByUserId(String id);

    List<Offer> getOffersByAdvertId(String id);
}
