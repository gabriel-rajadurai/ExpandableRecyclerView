package com.gabriel.expandablerecyclerview_customized.view_holders;

import android.view.View;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.gabriel.expandablerecyclerview_customized.R;
import com.gabriel.expandablerecyclerview_customized.models.ChildModel;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Gabriel on 19-03-2018.
 */

public class ChildItemViewHolder2 extends ChildViewHolder implements View.OnClickListener {

    private TextView tvChild;

    private View viewForeground;
    private View viewBackground;
    public SwipeRevealLayout swipeRevealLayout;

    private ChildItemDeleteListener mListener;

    public ChildItemViewHolder2(View itemView, final ChildItemDeleteListener mListener) {
        super(itemView);
        this.mListener = mListener;

        swipeRevealLayout = (SwipeRevealLayout) itemView;

        viewBackground = itemView.findViewById(R.id.view_background);
        viewForeground = itemView.findViewById(R.id.view_foreground);

        viewForeground.setOnClickListener(this);
        viewBackground.setOnClickListener(this);

        tvChild = itemView.findViewById(R.id.tv_child);
    }

    @Override
    public void onClick(View view) {
        if (view == viewForeground) {
            if (swipeRevealLayout.isOpened()) {
                swipeRevealLayout.close(true);
            }
        } else if (view == viewBackground) {
            swipeRevealLayout.close(true);
            mListener.onDelete(getAdapterPosition());
        }
    }

    public void bindData(ChildModel childModel) {
        tvChild.setText(childModel.getChildName());
    }

    public interface ChildItemDeleteListener {
        void onDelete(int position);
    }
}
