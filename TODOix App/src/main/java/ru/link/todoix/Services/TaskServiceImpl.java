package ru.link.todoix.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.link.todoix.Objects.*;
import ru.link.todoix.Repositories.TaskRepository;

import java.util.*;

/**
 * Сервис для работы с репозиторием дел
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void create(TaskDTO taskDTO) {
        TaskEntity taskEntity = Converter.DTOToEntity(taskDTO);
        taskRepository.save(taskEntity);
    }

    @Override
    public TaskDTO findById(UUID id) {
        TaskEntity taskEntity = taskRepository.findById(id);
        return Converter.entityToDTO(taskEntity);
    }

    @Override
    public void update(TaskDTO taskDTO) {
        taskRepository.updateById(
                taskDTO.getId(),
                taskDTO.getName(),
                taskDTO.getDescription(),
                taskDTO.getPriority(),
                taskDTO.isFinished(),
                taskDTO.getModifyDate()
        );
    }

    @Override
    public void deleteById(UUID id) {
        taskRepository.deleteById(id); // TODO: а как же броыить not found ???
    }

    @Override
    public List<TaskDTO> getAll() {
        List<TaskEntity> out = taskRepository.findAll();
        return Converter.convertListOfTaskEntitiesToDTO(out);
    }

    @Override
    public List<TaskDTO> getPage(int p, int size, String sort) {
        Pageable pageable = PageRequest.of(p, size, Sort.by(sort));
        Page<TaskEntity> page = taskRepository.findAll(pageable);
        return Converter.convertListOfTaskEntitiesToDTO(page.getContent());
    }

    @Override
    public List<TaskDTO> findByList(ListDTO listDTO) {
        List<TaskEntity> taskEntities = taskRepository.findByList(Converter.DTOToEntity(listDTO));
        return Converter.convertListOfTaskEntitiesToDTO(taskEntities);
    }
}
