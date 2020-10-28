package ru.link.todoix.Objects;

public class Converter {

    public static ListDTO ListEntityToDTO(ListEntity listEntity){
        ListDTO listDTO = new ListDTO();
        listDTO.setId(listEntity.getListId());
        listDTO.setName(listEntity.getName());
        listDTO.setCreateDate(listEntity.getCreateDate());
        listDTO.setModifyDate(listEntity.getModifyDate());
        return listDTO;
    }

    public static ListEntity ListDTOToEntity(ListDTO listDTO){
        ListEntity listEntity = new ListEntity();
        listEntity.setListId(listDTO.getId());
        listEntity.setName(listDTO.getName());
        listEntity.setCreateDate(listDTO.getCreateDate());
        listEntity.setModifyDate(listDTO.getModifyDate());
        return listEntity;
    }

}
