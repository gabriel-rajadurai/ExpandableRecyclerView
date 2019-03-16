package com.gabriel.expandablerecyclerview_customized.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Gabriel on 19-03-2018.
 */

public class GroupModel extends ExpandableGroup<ChildModel> {

    private String groupName;
    private List<ChildModel> items;

    public GroupModel(String groupName, List<ChildModel> items) {
        super(groupName, items);
        this.groupName = groupName;
        this.items = items;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ChildModel> getItems() {
        return items;
    }

    public void setItems(List<ChildModel> items) {
        this.items = items;
    }
}
