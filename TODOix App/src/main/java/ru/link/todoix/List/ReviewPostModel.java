package ru.link.todoix.List;

import java.util.List;

public class ReviewPostModel {
    private int finishedListCount = 0;
    private int openedListCount = 0;

    private List<Entity> lists;

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

    public List<Entity> getLists() {
        return lists;
    }

    public void setLists(List<Entity> lists) {
        this.lists = lists;
    }
}
