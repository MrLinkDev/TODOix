package ru.link.todoix.List;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.id.UUIDGenerationStrategy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "lists", schema = "todoix_app", catalog = "todoix")
public class ListEntity {

    private UUID listId;
    private String name;
    private Timestamp createDate;
    private Timestamp modifyDate;

    public ListEntity(){}

    public ListEntity(String name){
        this.name = name;

        listId = UUID.randomUUID();
        createDate = modifyDate = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @Column(name = "_id")
    public UUID getListId() {
        return listId;
    }

    public void setListId(UUID listId) {
        this.listId = listId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "modify_date")
    public Timestamp getModifyDate() {
        return modifyDate;
    }

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
