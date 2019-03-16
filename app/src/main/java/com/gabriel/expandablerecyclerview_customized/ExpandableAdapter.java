package com.gabriel.expandablerecyclerview_customized;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;


import com.gabriel.expandablerecyclerview_customized.models.ChildModel;
import com.gabriel.expandablerecyclerview_customized.models.GroupModel;
import com.gabriel.expandablerecyclerview_customized.view_holders.ChildItemViewHolder;
import com.gabriel.expandablerecyclerview_customized.view_holders.TitleViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 19-03-2018.
 */

public class ExpandableAdapter
        extends ExpandableRecyclerViewAdapter<TitleViewHolder, ChildItemViewHolder>
        implements ChildItemViewHolder.ChildItemClickedListener {

    private Context mContext;
    private List<GroupModel> groupModels = new ArrayList<>();

    public ExpandableAdapter(Context mContext,
                             List<? extends ExpandableGroup> groups,
                             boolean shouldOthersCollapse) {
        super(groups, shouldOthersCollapse);
        this.mContext = mContext;

        groupModels = (List<GroupModel>) groups;
    }

    @Override
    public TitleViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        return new TitleViewHolder(
                parent.getContext(),
                inflater.inflate(R.layout.title_item_layout, parent, false));
    }

    @Override
    public ChildItemViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        return new ChildItemViewHolder(
                inflater.inflate(R.layout.child_item_layout, parent, false),
                this);
    }

    @Override
    public void onBindChildViewHolder(ChildItemViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        holder.bindData((ChildModel) group.getItems().get(childIndex));
    }

    @Override
    public void onBindGroupViewHolder(TitleViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setTitle(group);
    }

    @Override
    public void onChildItemClicked(int position) {

        ExpandableListPosition listPos = expandableList.getUnflattenedPosition(position);

        GroupModel groupModel = groupModels.get(listPos.groupPos);

        Toast.makeText(mContext,
                "Group Name: "
                        + groupModel.getGroupName()
                        + ", Child Name: "
                        + groupModel.getItems().get(listPos.childPos).getChildName(),
                Toast.LENGTH_LONG
        ).show();
    }
}
