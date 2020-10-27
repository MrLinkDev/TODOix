package ru.link.todoix.Objects;

import java.util.*;

/**
 * Модель возвращаемых данных для списка дел
 */
public class ListPostModel{
    private UUID id;

    private String name;

    private Date createDate;
    private Date modifyDate;

    private int finishedCount = 0;
    private int openedCount = 0;

    private List<CaseEntity> cases;

    /**
     * Пустой конструктор
     */
    public ListPostModel(){}

    /**
     * Конструктор создания возвращаемой модели
     * @param listEntity - сущность списка дел
     */
    public ListPostModel(ListEntity listEntity){
        id = listEntity.getListId();
        name = listEntity.getName();
        createDate = listEntity.getCreateDate();
        modifyDate = listEntity.getModifyDate();
    }

    /**
     * Получение UUID списка
     * @return UUID списка
     */
    public UUID getId(){
        return id;
    }

    /**
     * Присвоение списку UUID
     * @param id - новый UUID
     */
    public void setId(UUID id){
        this.id = id;
    }

    /**
     * Получение имени списка дел
     * @return name - имя списка
     */
    public String getName(){
        return name;
    }

    /**
     * Присвоение списку нового имени
     * @param name - новое имя списка
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Получение даты создания списка
     * @return createDate - дата создания списка
     */
    public Date getCreateDate(){
        return createDate;
    }

    /**
     * Присвоение новой даты создания списку дел
     * @param createDate - дата создания списка
     */
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    /**
     * Получение даты изменения списка
     * @return modifyDate - дата изменения списка
     */
    public Date getModifyDate(){
        return modifyDate;
    }

    /**
     * Присвоение новой даты изменения списка
     * @param modifyDate - дата изменения списка
     */
    public void setModifyDate(Date modifyDate){
        this.modifyDate = modifyDate;
    }

    /**
     * Получение дел, привязанных к списку
     * @return cases - дела, привязанные к списку
     */
    public List<CaseEntity> getCases(){
        return cases;
    }

    /**
     * Присвоение дел, привязанных к списку дел;
     * подсчёт завершённых и открытых дел
     * @param cases - дела, привязанные к списку
     */
    public void setCases(List<CaseEntity> cases){
        this.cases = cases;

        for(CaseEntity c: cases)
            if (c.isFinished()) ++finishedCount;
            else ++openedCount;
    }

    /**
     * Получение количества завершённых дел в списке
     * @return finishedCount - количество завершённых дел в списке
     */
    public int getFinishedCount() {
        return finishedCount;
    }

    /**
     * Получение количества открытых дел в списке
     * @return openedCount - количество открытых дел в списке
     */
    public int getOpenedCount() {
        return openedCount;
    }
}
