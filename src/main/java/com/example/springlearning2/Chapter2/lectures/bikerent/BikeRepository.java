package com.example.springlearning2.Chapter2.lectures.bikerent;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public class BikeRepository {
//    private final EntityManager entityManager;
////    entity manager odpowiada za całą logikę DAO
//
//    public BikeRepository(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Transactional
//    public void save(Bike bike) {
//
//        //A co za tym idzie zmiany będą odzwierciedlone w bazie dnaych
//        //bike.setModel("AAA123");
//        if (exist(bike)) {
//            entityManager.merge(bike);
//        } else {
//            entityManager.persist(bike); //Wprowadzamy obiekt w stan zarządzany za pomocą enityManagera
//        }
//    }
//
//    private boolean exist(Bike bike) {
//        return entityManager.find(Bike.class, bike.getId()) != null;
//    }
//
//    public Optional<Bike> findByI(long id) {
//        return Optional.ofNullable(entityManager.find(Bike.class, id));
//    }
//
//    @Transactional
//    public void deleteById(Long id) {
//        findByI(id).ifPresent(entityManager::remove);
//    }
//
//    @Transactional
//    public void delete(Bike bike) {
//        // entityManager.remove(bike); to nie zadziała ponieważ dajemy mu obiekt który jest odłączony, potrzebujemy obiekt Managed
//        Bike mergedEntity = entityManager.merge(bike); //Tutaj dodajemy obiekt do zarządzania
//        entityManager.remove(mergedEntity); //Tutaj obiekt usuwamy- wprowadzamy w stan usunięty
//    }
// PRZED UŻYCIEM JPA TAKIE COŚ MOŻE BYĆ
//}
interface BikeRepository extends CrudRepository<Bike, Long> {
    //To pozwala zaoszczędzić ogromną ilość miejsca i kodu
    Optional<Bike> findBySerialNumberIgnoreCase(String serialNumber);

    int countAllByBorrowerIdIsNotNull();
    List<Bike> findAllByBorrowerIdIsNullOrderByDayPriceAsc();

}
