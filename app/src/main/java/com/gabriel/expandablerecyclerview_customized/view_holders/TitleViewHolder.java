package com.gabriel.expandablerecyclerview_customized.view_holders;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabriel.expandablerecyclerview_customized.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by Gabriel on 19-03-2018.
 */

public class TitleViewHolder extends GroupViewHolder {

    private Context mContext;
    private TextView tvTitle;
    private ImageView ivExpandCollapse;

    public TitleViewHolder(Context mContext, View itemView) {

        super(itemView);
        this.mContext = mContext;

        tvTitle = itemView.findViewById(R.id.tv_title);
        ivExpandCollapse = itemView.findViewById(R.id.iv_expand_collapse);
    }

    @Override
    public void expand() {

        ivExpandCollapse.setImageDrawable(ContextCompat.getDrawable(
                mContext,
                R.drawable.ic_expand_less_24dp
        ));

    }

    @Override
    public void collapse() {

        ivExpandCollapse.setImageDrawable(ContextCompat.getDrawable(
                mContext,
                R.drawable.ic_expand_more_24dp
        ));

    }

    public void setTitle(ExpandableGroup group) {

        tvTitle.setText(group.getTitle());
    }
}
