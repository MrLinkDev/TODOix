package ru.link.todoix.List;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Сущность списка дел
 */
@javax.persistence.Entity
@Table(name = "lists", schema = "todoix_app", catalog = "todoix")
public class Entity {
    private UUID listId;

    private String name;

    private Timestamp createDate;
    private Timestamp modifyDate;

    /**
     * Пустой конструктор для создания списка дел
     */
    public Entity(){}

    /**
     * Конструктор для создания списка дел
     * @param name - имя списка дел
     */
    public Entity(String name){
        this.name = name;

        listId = UUID.randomUUID();
        createDate = modifyDate = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Получение UUID списка
     * @return UUID списка
     */
    @Id
    @Column(name = "_id")
    public UUID getListId() {
        return listId;
    }

    /**
     * Присвоение списку UUID
     * @param listId - новый UUID
     */
    public void setListId(UUID listId) {
        this.listId = listId;
    }

    /**
     * Получение имени списка дел
     * @return name - имя списка
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Присвоение списку нового имени
     * @param name - новое имя списка
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получение даты создания списка
     * @return createDate - дата создания списка
     */
    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Присвоение новой даты создания списку дел
     * @param createDate - дата создания списка
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * Получение даты изменения списка
     * @return modifyDate - дата изменения списка
     */
    @Basic
    @Column(name = "modify_date")
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    /**
     * Присвоение новой даты изменения списка
     * @param modifyDate - дата изменения списка
     */
    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity that = (Entity)o;
        return listId == that.listId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listId, name);
    }
}
