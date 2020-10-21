package ru.link.todoix.List;

import ru.link.todoix.Case.CaseEntity;

import java.sql.Timestamp;
import java.util.*;

public class ListPostModel{
    private UUID id;
    private String name;
    private Timestamp createDate;
    private Timestamp modifyDate;
    private List<CaseEntity> cases;

    public ListPostModel(){}

    public ListPostModel(ListEntity listEntity){
        id = listEntity.getListId();
        name = listEntity.getName();
        createDate = listEntity.getCreateDate();
        modifyDate = listEntity.getModifyDate();
    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Timestamp getCreateDate(){
        return createDate;
    }

    public void setCreateDate(Timestamp createDate){
        this.createDate = createDate;
    }

    public Timestamp getModifyDate(){
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate){
        this.modifyDate = modifyDate;
    }

    public List<CaseEntity> getCases(){
        return cases;
    }

    public void setCases(List<CaseEntity> cases){
        this.cases = cases;
    }
}
