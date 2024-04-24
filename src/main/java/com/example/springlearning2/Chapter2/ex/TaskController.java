package com.example.springlearning2.Chapter2.ex;

import com.example.springlearning2.Chapter2.ex.DTO.NewTaskDto;
import com.example.springlearning2.Chapter2.ex.DTO.TaskDurationDto;
import com.example.springlearning2.Chapter2.ex.Exception.TaskAlreadyCompletedException;
import com.example.springlearning2.Chapter2.ex.Exception.TaskAlreadyStartedException;
import com.example.springlearning2.Chapter2.ex.Exception.TaskNotFoundException;
import com.example.springlearning2.Chapter2.ex.Exception.TaskNotStartedException;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Scanner;

@Controller
class TaskController {
    private final TaskService taskService;
    private final Scanner scanner;

    public TaskController(TaskService taskService, Scanner scanner) {
        this.taskService = taskService;
        this.scanner = scanner;
    }

    public void loop() {
        Option option;
        do {
            printOptions();
            option = chooseOption();
            evaluateOption(option);
        } while (option != Option.EXIT);
    }

    private void printOptions() {
        System.out.println("\nWybierz opcję:");
        for (Option option : Option.values()) {
            System.out.println(option);
        }
    }

    private Option chooseOption() {
        int optionNumber = scanner.nextInt();
        scanner.nextLine();
        return Option.fromInt(optionNumber);
    }

    private void evaluateOption(Option option) {
        try {
            switch (option) {
                case ADD -> addTask();
                case PRINT_SINGLE -> printTask();
                case START_TASK -> startTask();
                case COMPLETE_TASK -> completeTask();
                case PRINT_UNSTARTED -> printUnstartedTasks();
                case PRINT_COMPLETED -> printCompletedTasks();
                case EXIT -> exit();
            }
        } catch (TaskNotFoundException e) {
            System.out.println("Brak zadania ze wskazanym identyfikatorem");
        }
    }

    private void addTask() {
        System.out.println("Podaj tytuł zadania:");
        String title = scanner.nextLine();
        System.out.println("Opis zadania:");
        String description = scanner.nextLine();
        System.out.println("Priorytet (wyższa liczba = wyższy priorytet):");
        int priority = scanner.nextInt();
        scanner.nextLine();
        NewTaskDto task = new NewTaskDto(title, description, priority);
        Long savedTaskId = taskService.saveTask(task);
        System.out.println("Zadanie zapisane z identyfikatorem " + savedTaskId);
    }


    private void startTask() {
        System.out.println("Podaj identyfikator zadania:");
        long taskId = scanner.nextLong();
        scanner.nextLine();
        try {
            LocalDateTime taskStartTime = taskService.startTask(taskId);
            System.out.println("Czas rozpoczęcia zadania: " + taskStartTime);
        } catch (TaskAlreadyStartedException e) {
            System.out.println("To zadanie zostało już wcześniej wystartowane");
        }
    }

    private void completeTask() {
        System.out.println("Podaj identyfikator zadania: ");
        Long taskId = scanner.nextLong();
        scanner.nextLine();
        try {
            TaskDurationDto taskDurationDto = taskService.completeTask(taskId);
            System.out.println(taskDurationDto);
        } catch (TaskAlreadyCompletedException e) {
            System.out.println("Zadanie zostało już wcześniej zakończone");
        } catch (TaskNotStartedException e) {
            System.out.println("Wstartuj zadania zanim je wcześniej zakończysz.");
        }
    }

    private void printTask() {
        System.out.println("Podaj identyfikator zadania: ");
        long taskId = scanner.nextLong();
        scanner.nextLine();
        taskService.getTaskInfo(taskId)
                .ifPresentOrElse(System.out::println, () -> System.out.println("Brak wpisy o takim id"));
    }

    private void printUnstartedTasks() {
        taskService.getAllNotStartedTasksInfo()
                .forEach(System.out::println);
    }

    private void printCompletedTasks() {
        taskService.getAllCompletedTasksInfo()
                .forEach(System.out::println);
    }


    private void exit() {
        System.out.println("Koniec programu!");
    }

    private enum Option {
        ADD(1, "Dodaj nowe zadanie"),
        PRINT_SINGLE(2, "Wyświetl zadanie"),
        START_TASK(3, "Wystartuj zadanie"),
        PRINT_UNSTARTED(4, "Wyświetl nierpoczęte zadania"),
        PRINT_COMPLETED(5, "Wyświetl zakończone zadania"),
        COMPLETE_TASK(6, "Zakończ zadanie"),
        EXIT(7, "Koniec programu");

        private final int number;
        private final String name;

        Option(int number, String name) {
            this.number = number;
            this.name = name;
        }

        static Option fromInt(int option) {
            return values()[option - 1];
        }

        @Override
        public String toString() {
            return number + " - " + name;
        }
    }
}
