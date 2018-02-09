package com.example.stock.db;

import java.util.List;

public interface GroupDataSource {
    List<Group> getGroups();

    void insertOrUpdateGroup(Group group);

    Group getGroup(int id);

}
