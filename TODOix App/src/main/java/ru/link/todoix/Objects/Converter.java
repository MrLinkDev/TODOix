package ru.link.todoix.Objects;

import java.util.*;

/**
 * Класс для конвертации DTO в Entity и обратно
 */
public class Converter {

    /**
     * Конвертация ListEntity в ListDTO
     * @param listEntity - сущность списка дел
     * @return ListDTO - DTO списка дел
     */
    public static ListDTO entityToDTO(ListEntity listEntity){
        if (listEntity == null) return null;
        ListDTO listDTO = new ListDTO();
        listDTO.setId(listEntity.getListId());
        listDTO.setName(listEntity.getName());
        listDTO.setCreateDate(listEntity.getCreateDate());
        listDTO.setModifyDate(listEntity.getModifyDate());
        return listDTO;
    }

    /**
     * Конвертация ListDTO в ListEntity
     * @param listDTO - DTO списка дел
     * @return ListEntity - сущность списка дел
     */
    public static ListEntity DTOToEntity(ListDTO listDTO){
        ListEntity listEntity = new ListEntity();
        listEntity.setListId(listDTO.getId());
        listEntity.setName(listDTO.getName());
        listEntity.setCreateDate(listDTO.getCreateDate());
        listEntity.setModifyDate(listDTO.getModifyDate());
        return listEntity;
    }

    /**
     * Конвертация списка ListEntity в список ListDTO
     * @param list - список сущностей списка дел
     * @return List<ListDTO> - список DTO списка дел
     */
    public static List<ListDTO> convertListOfListEntitiesToDTO(List<ListEntity> list){
        List<ListDTO> DTOList = new ArrayList<>();
        for (ListEntity listEntity : list){
            ListDTO cache = new ListDTO();

            cache.setId(listEntity.getListId());
            cache.setName(listEntity.getName());
            cache.setCreateDate(listEntity.getCreateDate());
            cache.setModifyDate(listEntity.getModifyDate());

            DTOList.add(cache);
        }
        return DTOList;
    }

    /**
     * Конвертация TaskEntity в TaskDTO
     * @param taskEntity - сущность дела
     * @return ListDTO - DTO дела
     */
    public static TaskDTO entityToDTO(TaskEntity taskEntity){
        if (taskEntity == null) return null;
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(taskEntity.getId());
        taskDTO.setListId(entityToDTO(taskEntity.getTaskListId()));
        taskDTO.setName(taskEntity.getName());
        taskDTO.setDescription(taskEntity.getDescription());
        taskDTO.setPriority(taskEntity.getPriority());
        taskDTO.setFinished(taskEntity.isFinished());
        taskDTO.setCreateDate(taskEntity.getCreateDate());
        taskDTO.setModifyDate(taskEntity.getModifyDate());
        return taskDTO;
    }

    /**
     * Конвертация TaskDTO в TaskEntity
     * @param taskDTO - DTO дела
     * @return TaskEntity - сущность дела
     */
    public static TaskEntity DTOToEntity(TaskDTO taskDTO){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskDTO.getId());
        taskEntity.setTaskListId(DTOToEntity(taskDTO.getListId()));
        taskEntity.setName(taskDTO.getName());
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setPriority(taskDTO.getPriority());
        taskEntity.setFinished(taskDTO.isFinished());
        taskEntity.setCreateDate(taskDTO.getCreateDate());
        taskEntity.setModifyDate(taskDTO.getModifyDate());
        return taskEntity;
    }

    /**
     * Конвертация списка TaskEntity в список TaskDTO
     * @param entityList - список сущностей дел
     * @return List<TaskDTO> - список DTO дел
     */
    public static List<TaskDTO> convertListOfTaskEntitiesToDTO(List<TaskEntity> entityList){
        List<TaskDTO> DTOTask = new ArrayList<>();
        for (TaskEntity taskEntity : entityList){
            TaskDTO cache = new TaskDTO();

            cache.setId(taskEntity.getId());
            cache.setListId(entityToDTO(taskEntity.getTaskListId()));
            cache.setName(taskEntity.getName());
            cache.setDescription(taskEntity.getDescription());
            cache.setPriority(taskEntity.getPriority());
            cache.setFinished(taskEntity.isFinished());
            cache.setCreateDate(taskEntity.getCreateDate());
            cache.setModifyDate(taskEntity.getModifyDate());

            DTOTask.add(cache);
        }
        return DTOTask;
    }
}
