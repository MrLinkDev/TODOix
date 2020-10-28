package ru.link.todoix.PostModels;

import ru.link.todoix.Objects.ListEntity;

import java.util.List;

public class ReviewPostModel {
    private int finishedListCount = 0;
    private int openedListCount = 0;

    private List<ListEntity> lists;

    public int getFinishedListCount() {
        return finishedListCount;
    }

    public void setFinishedListCount(int finishedListCount) {
        this.finishedListCount = finishedListCount;
    }

    public int getOpenedListCount() {
        return openedListCount;
    }

    public void setOpenedListCount(int openedListCount) {
        this.openedListCount = openedListCount;
    }

    public List<ListEntity> getLists() {
        return lists;
    }

    public void setLists(List<ListEntity> lists) {
        this.lists = lists;
    }
}
