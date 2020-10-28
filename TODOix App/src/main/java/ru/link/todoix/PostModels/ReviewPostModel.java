package ru.link.todoix.PostModels;

import ru.link.todoix.Objects.*;

import java.util.List;

public class ReviewPostModel {
    private int finishedListCount = 0;
    private int openedListCount = 0;

    private List<ListDTO> lists;

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

    public List<ListDTO> getLists() {
        return lists;
    }

    public void setLists(List<ListDTO> lists) {
        this.lists = lists;
    }
}
