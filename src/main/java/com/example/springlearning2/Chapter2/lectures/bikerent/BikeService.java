package com.example.springlearning2.Chapter2.lectures.bikerent;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {
    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public void add(BikeDTO newBike) {
        Bike bike = new Bike(newBike.getId(),
                newBike.getModel(),
                newBike.getSerialNo(),
                newBike.getHourPrice(),
                newBike.getDayPrice());
        bikeRepository.save(bike);
    }

    @Transactional
    public void deleteById(Long bikeId) {
        bikeRepository.deleteById(bikeId);
    }

    @Transactional
    public double rentForHours(String serialNo, int hours, String borowerId) {
        LocalDateTime dateOfReturn = LocalDateTime.now().plusHours(hours);
        Bike bike = updateBike(serialNo, borowerId, dateOfReturn);
        return bike.getHourPrice() * hours;
    }

    @Transactional
    public double rentForDay(String serialNo, String borowerId) {
        LocalDateTime dateOfReturn = LocalDateTime.now().plusHours(23).plusMinutes(59);
        Bike bike = updateBike(serialNo, borowerId, dateOfReturn);
        return bike.getDayPrice();
    }

    @Transactional
    public void returnBike(String serialNo) {
        updateBike(serialNo, null, null);
    }

    private Bike updateBike(String serialNo, String borowerId, LocalDateTime dateOfReturn) {
        Bike bike = bikeRepository.findBySerialNumberIgnoreCase(serialNo)
                .orElseThrow(BikeNotFoundException::new);
        bike.setDateOfReturn(dateOfReturn);
        bike.setBorrowerId(borowerId);
        bikeRepository.save(bike); //#1 pierwsze podejście na aktualizacje obiektów
        return bike;
    }

    //Działamy na DTO zamiast na encjach żeby nie udostępniać za dużo informacji
    public int countBorrowedBikes() {
        return bikeRepository.countAllByBorrowerIdIsNotNull();
    }

    public List<BikeDTO> findAllAvalibleBikes() {
        return bikeRepository.findAllByBorrowerIdIsNullOrderByDayPriceAsc()
                .stream().map(bike -> new BikeDTO(
                        bike.getId(),
                        bike.getModel(),
                        bike.getSerialNo(),
                        bike.getHourPrice(),
                        bike.getDayPrice()
                )).collect(Collectors.toList());
    }

}
