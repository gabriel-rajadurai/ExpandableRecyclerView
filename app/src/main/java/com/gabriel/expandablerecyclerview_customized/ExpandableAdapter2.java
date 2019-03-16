package com.gabriel.expandablerecyclerview_customized;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.gabriel.expandablerecyclerview_customized.models.ChildModel;
import com.gabriel.expandablerecyclerview_customized.models.ChildModel;
import com.gabriel.expandablerecyclerview_customized.models.GroupModel;
import com.gabriel.expandablerecyclerview_customized.view_holders.ChildItemViewHolder2;
import com.gabriel.expandablerecyclerview_customized.view_holders.TitleViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 19-03-2018.
 */

public class ExpandableAdapter2
        extends ExpandableRecyclerViewAdapter<TitleViewHolder, ChildItemViewHolder2>
        implements ChildItemViewHolder2.ChildItemDeleteListener {

    private Context mContext;
    private List<GroupModel> groupModels = new ArrayList<>();

    private ViewBinderHelper binderHelper;

    public ExpandableAdapter2(Context mContext,
                              List<? extends ExpandableGroup> groups,
                              boolean shouldOthersCollapse) {
        super(groups, shouldOthersCollapse);
        this.mContext = mContext;

        groupModels = (List<GroupModel>) groups;
        binderHelper = new ViewBinderHelper();
        binderHelper.setOpenOnlyOne(true);
    }

    @Override
    public TitleViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        return new TitleViewHolder(
                parent.getContext(),
                inflater.inflate(R.layout.title_item_layout, parent, false));
    }

    @Override
    public ChildItemViewHolder2 onCreateChildViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        return new ChildItemViewHolder2(
                inflater.inflate(R.layout.child_item_layout_2, parent, false),
                this);
    }

    @Override
    public boolean onGroupClick(int flatPos) {
        return super.onGroupClick(flatPos);
    }

    @Override
    public void onGroupExpanded(int positionStart, int itemCount) {
        super.onGroupExpanded(positionStart, itemCount);
    }

    @Override
    public void onGroupCollapsed(int positionStart, int itemCount) {
        super.onGroupCollapsed(positionStart, itemCount);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onAllGroupsCollapsed() {
        super.onAllGroupsCollapsed();
    }

    @Override
    public void onBindChildViewHolder(ChildItemViewHolder2 holder, int flatPosition, ExpandableGroup group, final int childIndex) {

        final ChildModel childModel = (ChildModel) group.getItems().get(childIndex);

        holder.swipeRevealLayout.setSwipeListener(new SwipeRevealLayout.SwipeListener() {
            @Override
            public void onClosed(SwipeRevealLayout view) {
                childModel.setSwipe_state(ChildModel.SWIPE_STATE.OPEN);
            }

            @Override
            public void onOpened(SwipeRevealLayout view) {
                childModel.setSwipe_state(ChildModel.SWIPE_STATE.CLOSED);
            }

            @Override
            public void onSlide(SwipeRevealLayout view, float slideOffset) {

            }
        });

        binderHelper.bind(holder.swipeRevealLayout,
                childModel.getId());

        holder.bindData(childModel);
    }

    @Override
    public void onBindGroupViewHolder(TitleViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setTitle(group);
    }

    @Override
    public void onDelete(int position) {
        ExpandableListPosition listPos = expandableList.getUnflattenedPosition(position);

        if (listPos.type == ExpandableListPosition.GROUP) return;

        GroupModel groupModel = groupModels.get(listPos.groupPos);

        groupModel.getItems().remove(listPos.childPos);
        notifyItemRemoved(position);

        if (groupModel.getItems().size() == 0) {
            groupModels.remove(listPos.groupPos);
            notifyGroupRemoved(listPos.groupPos);
        }

    }
}
