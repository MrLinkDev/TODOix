package ru.link.todoix.List;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;

@Entity
@Table(name = "lists")
public class ListItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private Long listId;

    @Column(name = "name")
    public String name;

    @Column(name = "create_date")
    public Date createDate;

    @Column(name = "modify_date")
    public Date modifyDate;

    public ListItem(){}

    public ListItem(String name){
        this.name = name;

        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        createDate = modifyDate = new Date(currentDate.getTime());
    }

    public Long getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
