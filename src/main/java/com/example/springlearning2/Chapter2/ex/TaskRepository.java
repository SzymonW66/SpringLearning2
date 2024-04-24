package com.example.springlearning2.Chapter2.ex;


import org.springframework.data.repository.CrudRepository;

import java.util.List;


interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAllByStartTimeIsNullOrderByPriorityDesc();
    List<Task> findAllByCompletionTimeNotNullOrderByCompletionTimeDesc();
}

