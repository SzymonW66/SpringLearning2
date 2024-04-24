package com.example.springlearning2.Chapter2.ex;

import com.example.springlearning2.Chapter2.ex.DTO.NewTaskDto;
import com.example.springlearning2.Chapter2.ex.DTO.TaskDurationDto;
import com.example.springlearning2.Chapter2.ex.Exception.TaskAlreadyCompletedException;
import com.example.springlearning2.Chapter2.ex.Exception.TaskAlreadyStartedException;
import com.example.springlearning2.Chapter2.ex.Exception.TaskNotFoundException;
import com.example.springlearning2.Chapter2.ex.Exception.TaskNotStartedException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private static long nextId = 1;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Long saveTask(NewTaskDto newTaskDto) {
        Task taskToSave = new Task(newTaskDto.getTitle(), newTaskDto.getDescription(), newTaskDto.getPriority());
        taskToSave.setId(nextId);
        Task savedTask = taskRepository.save(taskToSave);
        nextId++;
        return savedTask.getId();
    }

    public Optional<String> getTaskInfo(Long taskId) {
        return taskRepository.findById(taskId).map(Task::toString);
    }

    @Transactional
    public LocalDateTime startTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if (task.getStartTime() != null) {
            throw new TaskAlreadyStartedException();
        }
        task.setStartTime(LocalDateTime.now());
        return task.getStartTime();
    }

    @Transactional
    public TaskDurationDto completeTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskAlreadyStartedException::new);
        if (task.getStartTime() == null) {
            throw new TaskNotStartedException();
        } else if (task.getCompletionTime() != null) {
            throw new TaskAlreadyCompletedException();
        }
        task.setCompletionTime(LocalDateTime.now());
        return new TaskDurationDto(task.getStartTime(), task.getCompletionTime());
    }

    List<String> getAllNotStartedTasksInfo(){
        return taskRepository.findAllByStartTimeIsNullOrderByPriorityDesc()
                .stream()
                .map(Task::toString)
                .toList();
    }

    List<String> getAllCompletedTasksInfo(){
        return taskRepository.findAllByCompletionTimeNotNullOrderByCompletionTimeDesc()
                .stream()
                .map(Task::toString)
                .toList();
    }


}
