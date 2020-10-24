package ru.link.todoix.List;

import org.hibernate.annotations.*;
import org.hibernate.dialect.PostgreSQL95Dialect;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.*;

/**
 * Сущность списка дел
 */
@Embeddable
@Entity
@Table(name = "lists", schema = "todoix_app", catalog = "todoix")
public class ListEntity {
    public UUID listId;

    public String name;

    public Timestamp createDate;
    public Timestamp modifyDate;

    /**
     * Пустой конструктор для создания списка дел
     */
    public ListEntity(){}

    /**
     * Конструктор для создания списка дел
     * @param name - имя списка дел
     */
    public ListEntity(String name){
        this.name = name;

        listId = UUID.randomUUID();
        createDate = modifyDate = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Получение UUID списка
     * @return UUID списка
     */
    @Id
    @GeneratedValue
    @Column(name = "_id", nullable = false, insertable = true, updatable = false)
    public UUID getListId() {
        System.out.println(listId);
        return listId;
    }

    /**
     * Присвоение списку UUID
     * @param listId - новый UUID
     */
    public void setListId(java.util.UUID listId) {
        this.listId = listId;
    }

    /**
     * Получение имени списка дел
     * @return name - имя списка
     */
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true)
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
    @Column(name = "create_date", nullable = false, insertable = true, updatable = false)
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
    @Column(name = "modify_date", nullable = false, insertable = true, updatable = true)
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
        ListEntity that = (ListEntity)o;
        return listId == that.listId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listId, name);
    }
}
