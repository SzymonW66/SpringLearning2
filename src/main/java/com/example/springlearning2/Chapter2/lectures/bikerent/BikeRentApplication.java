package com.example.springlearning2.Chapter2.lectures.bikerent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BikeRentApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BikeRentApplication.class, args);
        BikeDTO bike1 = new BikeDTO(1L, "Kross Eskel", "KRS12345", 30, 100);
        BikeService bikeService = context.getBean(BikeService.class);
        bikeService.add(bike1);

        //        BikeRepository bikeRepository = context.getBean(BikeRepository.class); // korzystanie z encji
//        bikeRepository.save(bike1);
        //Obiekt po utrwaleniu zostaje odłączony po save
        System.out.println("Obiekt został zapisany");

        //Jak zachowuje się obiekt w stanie odłączonym (detached) i w stanie zarządzanym (managed)


//        Bike bike2 = new Bike(2L, "Bugatti rower", "KRS12435", 40, 110);
//        bikeRepository.save(bike2);
//        System.out.println("Zapisano rower nr 2");
//
//
//        System.out.println("Rower nr 1");
//        bikeRepository.findByI(1L).ifPresent(System.out::println);
//        System.out.println("Rower nr 2");
//        bikeRepository.findByI(2L).ifPresent(System.out::println);
//
//
//        System.out.println("Usuwam z bazy danych rower bike1");
//        bikeRepository.deleteById(1L);
//
//        System.out.println("Pobieram i wyświetlam Bike1");
//        bikeRepository.findByI(1L).ifPresentOrElse(System.out::println, () -> System.out.println("Nie ma takiego obiektu"));
    }

}
