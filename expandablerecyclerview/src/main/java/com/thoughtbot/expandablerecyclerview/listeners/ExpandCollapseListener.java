package com.thoughtbot.expandablerecyclerview.listeners;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

public interface ExpandCollapseListener {

    /**
     * Called when a group is expanded
     *
     * @param positionStart the flat position of the first child in the {@link ExpandableGroup}
     * @param itemCount     the total number of children in the {@link ExpandableGroup}
     */
    void onGroupExpanded(int positionStart, int itemCount);

    /**
     * Called when a group is collapsed
     *
     * @param positionStart the flat position of the first child in the {@link ExpandableGroup}
     * @param itemCount     the total number of children in the {@link ExpandableGroup}
     */
    void onGroupCollapsed(int positionStart, int itemCount);

    /**
     * Created by: Gabriel, 06/03/2018
     *
     * Callback to notify that all groups are collapsed now
     */
    void onAllGroupsCollapsed();
}
