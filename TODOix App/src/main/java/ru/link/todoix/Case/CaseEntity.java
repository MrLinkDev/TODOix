package ru.link.todoix.Case;

import javax.persistence.*;
import java.sql.*;
import java.util.*;

@Entity
@Table(name = "cases", schema = "todoix_app", catalog = "todoix")
public class CaseEntity {
    private UUID caseId;
    private UUID listId;

    private String name;
    private String description;

    private short urgency;

    private boolean finished;

    private Timestamp createDate;
    private Timestamp modifyDate;

    public CaseEntity(){}

    public CaseEntity(UUID listId, String name, String description, short urgency){
        this.listId = listId;
        this.name = name;
        this.description = description;
        this.urgency = urgency;

        caseId = UUID.randomUUID();
        finished = false;
        createDate = modifyDate = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @Column(name = "_id")
    public UUID getCaseId() {
        return caseId;
    }

    public void setCaseId(UUID caseId) {
        this.caseId = caseId;
    }

    @Basic
    @Column(name = "list_id")
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "urgency")
    public short getUrgency() {
        return urgency;
    }

    public void setUrgency(short urgency) {
        this.urgency = urgency;
    }

    @Basic
    @Column(name = "finished")
    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
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
        CaseEntity that = (CaseEntity)o;
        return caseId == that.caseId &&
                listId == that.listId &&
                urgency == that.urgency &&
                finished == that.finished &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(modifyDate, that.modifyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caseId, listId, name, description, urgency, finished, createDate, modifyDate);
    }
}
