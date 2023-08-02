package com.safalifter.jobservice.controller;

import com.safalifter.jobservice.dto.OfferDto;
import com.safalifter.jobservice.request.offer.MakeAnOfferRequest;
import com.safalifter.jobservice.request.offer.OfferUpdateRequest;
import com.safalifter.jobservice.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/offer")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @PostMapping("/makeAnOffer")
    public ResponseEntity<OfferDto> makeAnOffer(@RequestBody MakeAnOfferRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(offerService.makeAnOffer(request), OfferDto.class));
    }

    @GetMapping("/getOfferById/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable String id) {
        return ResponseEntity.ok(modelMapper.map(offerService.getOfferById(id), OfferDto.class));
    }

    @GetMapping("/getOffersByUserId/{id}")
    public ResponseEntity<List<OfferDto>> getOffersByUserId(@PathVariable String id) {
        return ResponseEntity.ok(offerService.getOffersByUserId(id).stream()
                .map(offer -> modelMapper.map(offer, OfferDto.class)).toList());
    }

    @GetMapping("/getOffersByAdvertId/{id}")
    public ResponseEntity<List<OfferDto>> getOffersByAdvertId(@PathVariable String id) {
        return ResponseEntity.ok(offerService.getOffersByAdvertId(id).stream()
                .map(offer -> modelMapper.map(offer, OfferDto.class)).toList());
    }

    @PutMapping("/update")
    public ResponseEntity<OfferDto> updateOfferById(@RequestBody OfferUpdateRequest request) {
        return ResponseEntity.ok(modelMapper.map(offerService.updateOfferById(request), OfferDto.class));
    }

    @DeleteMapping("/deleteOfferById/{id}")
    public ResponseEntity<Void> deleteOfferById(@PathVariable String id) {
        offerService.deleteOfferById(id);
        return ResponseEntity.ok().build();
    }
}
