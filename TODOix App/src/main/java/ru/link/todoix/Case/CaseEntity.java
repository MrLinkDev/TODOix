package ru.link.todoix.Case;

import javax.persistence.*;
import java.sql.*;
import java.util.*;

/**
 * Сущность дела
 */
@javax.persistence.Entity
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

    /**
     * Пустой конструктор
     */
    public CaseEntity(){}

    /**
     * Конструктор для создания дела
     * @param listId - UUID списка дел, к которому будет привязано дело
     * @param name - название дела
     * @param description - описание дела
     * @param urgency - срочность дела
     */
    public CaseEntity(UUID listId, String name, String description, short urgency){
        this.listId = listId;
        this.name = name;
        this.description = description;
        this.urgency = urgency;

        caseId = UUID.randomUUID();
        finished = false;
        createDate = modifyDate = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Получение UUID дела
     * @return caseId - UUID дела
     */
    @Id
    @Column(name = "_id")
    public UUID getCaseId() {
        return caseId;
    }

    /**
     * Присвоение нового UUID делу
     * @param caseId - новый UUID дела
     */
    public void setCaseId(UUID caseId) {
        this.caseId = caseId;
    }

    /**
     * Получение UUID списка дел, к которому привязано дело
     * @return listId - UUID списка дел
     */
    @Basic
    @Column(name = "list_id")
    public UUID getListId() {
        return listId;
    }

    /**
     * Присвоение дела новому списку дел
     * @param listId - UUID нового списка дел
     */
    public void setListId(UUID listId) {
        this.listId = listId;
    }

    /**
     * Получение названия дела
     * @return name - название дела
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Присвоение нового имени делу
     * @param name - новое название дела
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получение описания дела
     * @return description - описание дела
     */
    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     * Присвоение нового описания делу
     * @param description - новое описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Получение срочности дела
     * @return urgency - срочность дела
     */
    @Basic
    @Column(name = "urgency")
    public short getUrgency() {
        return urgency;
    }

    /**
     * Присвоение новой срочности делу
     * @param urgency - новая срочность
     */
    public void setUrgency(short urgency) {
        this.urgency = urgency;
    }

    /**
     * Проверка статуса дела
     * @return true - если завершено, false - если не завершено
     */
    @Basic
    @Column(name = "finished")
    public boolean isFinished() {
        return finished;
    }

    /**
     * Присвоение нового статуса делу
     * @param finished - статус дела
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Получение даты создания дела
     * @return createDate - новая дата создания дела
     */
    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Присвоение новой даты создания делу
     * @param createDate - новая дата создания
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * Получение даты изменения дела
     * @return modifyDate - новая дата изменения дела
     */
    @Basic
    @Column(name = "modify_date")
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    /**
     * Присвоение новой даты изменения делу
     * @param modifyDate - новая дата изменения
     */
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
