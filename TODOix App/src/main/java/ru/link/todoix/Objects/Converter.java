package ru.link.todoix.Objects;

import java.util.*;

public class Converter {

    public static ListDTO entityToDTO(ListEntity listEntity){
        ListDTO listDTO = new ListDTO();
        listDTO.setId(listEntity.getListId());
        listDTO.setName(listEntity.getName());
        listDTO.setCreateDate(listEntity.getCreateDate());
        listDTO.setModifyDate(listEntity.getModifyDate());
        return listDTO;
    }

    public static ListEntity DTOToEntity(ListDTO listDTO){
        ListEntity listEntity = new ListEntity();
        listEntity.setListId(listDTO.getId());
        listEntity.setName(listDTO.getName());
        listEntity.setCreateDate(listDTO.getCreateDate());
        listEntity.setModifyDate(listDTO.getModifyDate());
        return listEntity;
    }

    public static List<ListDTO> ListEntityToDTO(List<ListEntity> list){
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

    public static List<ListEntity> ListDTOToEntity(List<ListDTO> list){
        List<ListEntity> entityList = new ArrayList<>();
        for (ListDTO listDTO : list){
            ListEntity cache = new ListEntity();
            cache.setListId(listDTO.getId());
            cache.setName(listDTO.getName());
            cache.setCreateDate(listDTO.getCreateDate());
            cache.setModifyDate(listDTO.getModifyDate());
            entityList.add(cache);
        }
        return entityList;
    }

    public static CaseDTO entityToDTO(CaseEntity caseEntity){
        CaseDTO caseDTO = new CaseDTO();
        caseDTO.setCaseId(caseEntity.getCaseId());
        caseDTO.setListId(entityToDTO(caseEntity.getListId()));
        caseDTO.setName(caseEntity.getName());
        caseDTO.setDescription(caseEntity.getDescription());
        caseDTO.setPriority(caseEntity.getPriority());
        caseDTO.setFinished(caseEntity.isFinished());
        caseDTO.setCreateDate(caseEntity.getCreateDate());
        caseDTO.setModifyDate(caseEntity.getModifyDate());
        return caseDTO;
    }

    public static CaseEntity DTOToEntity(CaseDTO caseDTO){
        CaseEntity caseEntity = new CaseEntity();
        caseEntity.setCaseId(caseDTO.getCaseId());
        caseEntity.setListId(DTOToEntity(caseDTO.getListId()));
        caseEntity.setName(caseDTO.getName());
        caseEntity.setDescription(caseDTO.getDescription());
        caseEntity.setPriority(caseDTO.getPriority());
        caseEntity.setFinished(caseDTO.isFinished());
        caseEntity.setCreateDate(caseDTO.getCreateDate());
        caseEntity.setModifyDate(caseDTO.getModifyDate());
        return caseEntity;
    }

    public static List<CaseDTO> CaseEntityToDTO(List<CaseEntity> entityList){
        List<CaseDTO> DTOCase = new ArrayList<>();
        for (CaseEntity caseEntity : entityList){
            CaseDTO cache = new CaseDTO();

            cache.setCaseId(caseEntity.getCaseId());
            cache.setListId(entityToDTO(caseEntity.getListId()));
            cache.setName(caseEntity.getName());
            cache.setDescription(caseEntity.getDescription());
            cache.setPriority(caseEntity.getPriority());
            cache.setFinished(caseEntity.isFinished());
            cache.setCreateDate(caseEntity.getCreateDate());
            cache.setModifyDate(caseEntity.getModifyDate());

            DTOCase.add(cache);
        }
        return DTOCase;
    }

    public static List<CaseEntity> CaseDTOToEntity(List<CaseDTO> caseDTO){
        List<CaseEntity> entityList = new ArrayList<>();
        for (CaseDTO c : caseDTO){
            CaseEntity cache = new CaseEntity();

            cache.setCaseId(c.getCaseId());
            cache.setListId(DTOToEntity(c.getListId()));
            cache.setName(c.getName());
            cache.setDescription(c.getDescription());
            cache.setPriority(c.getPriority());
            cache.setFinished(c.isFinished());
            cache.setCreateDate(c.getCreateDate());
            cache.setModifyDate(c.getModifyDate());

            entityList.add(cache);
        }
        return entityList;
    }

}
