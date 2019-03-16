package com.thoughtbot.expandablerecyclerview;

import android.util.Log;

import com.thoughtbot.expandablerecyclerview.listeners.ExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableList;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is the sits between the backing {@link ExpandableList} and the {@link
 * ExpandableRecyclerViewAdapter} and mediates the expanding and collapsing of {@link
 * ExpandableGroup}
 */
public class ExpandCollapseController {

    private ExpandCollapseListener listener;
    private ExpandableList expandableList;

    public ExpandCollapseController(ExpandableList expandableList, ExpandCollapseListener listener) {
        this.expandableList = expandableList;
        this.listener = listener;
    }

    /**
     * Collapse a group
     *
     * @param listPosition position of the group to collapse
     */
    private void collapseGroup(ExpandableListPosition listPosition) {
        expandableList.expandedGroupIndexes[listPosition.groupPos] = false;
        if (listener != null) {
            listener.onGroupCollapsed(expandableList.getFlattenedGroupIndex(listPosition) + 1,
                    expandableList.groups.get(listPosition.groupPos).getItemCount());
        }
        checkIfAllGroupsCollapsed();
    }

    /**
     * Expand a group
     *
     * @param listPosition the group to be expanded
     */
    private void expandGroup(ExpandableListPosition listPosition) {
        expandableList.expandedGroupIndexes[listPosition.groupPos] = true;
        if (listener != null) {
            listener.onGroupExpanded(expandableList.getFlattenedGroupIndex(listPosition) + 1,
                    expandableList.groups.get(listPosition.groupPos).getItemCount());
        }
    }

    /**
     * Created by Gabriel - 02/03/2018
     * removes the state specified by groupPos in expandedGroupIndexes array to reflect that a group has been removed.
     * <p>
     * NOTE: This method is for internal purposes. Do not invoke this in your classes.
     *
     * @param groupPos the position of the group that was removed
     */
    void removeState(int groupPos) {

        //ExpandableListPosition listPosition = expandableList.getUnflattenedPosition(groupPos);
        boolean[] temp = new boolean[expandableList.expandedGroupIndexes.length - 1];

        int j = 0;
        for (int i = 0; i < expandableList.expandedGroupIndexes.length; i++) {
            if (i != groupPos) {
                temp[j] = expandableList.expandedGroupIndexes[i];
                j++;
            }
        }

        expandableList.expandedGroupIndexes = temp;

        checkIfAllGroupsCollapsed();
    }

    /**
     * Created by Gabriel - 02/03/2018
     * adds the state specified by groupPos in expandedGroupIndexes array to reflect that a group has been added.
     * <p>
     * NOTE: This method is for internal purposes. Do not invoke this in your classes.
     * NOTE: By default this will add the group in closed state. //TODO is another parameter needed for specifying what state?
     * @param groupPos the position where the group is to be added
     */
    void addState(int groupPos) {

        if (expandableList.expandedGroupIndexes.length == 0) {
            expandableList.expandedGroupIndexes = new boolean[1];
            expandableList.expandedGroupIndexes[0] = false;
            return;
        }

        boolean[] temp = new boolean[expandableList.expandedGroupIndexes.length + 1];

        int j = 0;
        for (int i = 0; i < expandableList.expandedGroupIndexes.length; i++) {
            if (i != groupPos) {
                temp[j] = expandableList.expandedGroupIndexes[i];
            } else {
                temp[j] = false;
            }
            j++;
        }

        expandableList.expandedGroupIndexes = temp;

        checkIfAllGroupsCollapsed();
    }

    private void checkIfAllGroupsCollapsed() {
        boolean allCollapsed = false;

        if (expandableList.expandedGroupIndexes.length != 0) {

            for (boolean expandedGroupIndex : expandableList.expandedGroupIndexes) {
                if (expandedGroupIndex)
                    return;
                else
                    allCollapsed = true;
            }
        } else {
            allCollapsed = true;
        }

        if (allCollapsed) {
            listener.onAllGroupsCollapsed();
        }
    }

    /**
     * @param group the {@link ExpandableGroup} being checked for its collapsed state
     * @return true if {@code group} is expanded, false if it is collapsed
     */
    public boolean isGroupExpanded(ExpandableGroup group) {
        int groupIndex = expandableList.groups.indexOf(group);
        return expandableList.expandedGroupIndexes[groupIndex];
    }

    /**
     * @param flatPos the flattened position of an item in the list
     * @return true if {@code group} is expanded, false if it is collapsed
     */
    public boolean isGroupExpanded(int flatPos) {
        ExpandableListPosition listPosition = expandableList.getUnflattenedPosition(flatPos);
        return expandableList.expandedGroupIndexes[listPosition.groupPos];
    }

    /**
     * @param flatPos The flat list position of the group
     * @return false if the group is expanded, *after* the toggle, true if the group is now collapsed
     */
    public boolean toggleGroup(int flatPos) {
        ExpandableListPosition listPos = expandableList.getUnflattenedPosition(flatPos);
        boolean expanded = expandableList.expandedGroupIndexes[listPos.groupPos];
        if (expanded) {
            collapseGroup(listPos);
        } else {
            expandGroup(listPos);
        }
        return expanded;
    }

    public boolean toggleGroup(ExpandableGroup group) {
        ExpandableListPosition listPos =
                expandableList.getUnflattenedPosition(expandableList.getFlattenedGroupIndex(group));
        boolean expanded = expandableList.expandedGroupIndexes[listPos.groupPos];
        if (expanded) {
            collapseGroup(listPos);
        } else {
            expandGroup(listPos);
        }
        return expanded;
    }

}