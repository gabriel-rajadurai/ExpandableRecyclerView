package com.gabriel.expandablerecyclerview_customized.view_holders;

import android.view.View;
import android.widget.TextView;

import com.gabriel.expandablerecyclerview_customized.R;
import com.gabriel.expandablerecyclerview_customized.models.ChildModel;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Gabriel on 19-03-2018.
 */

public class ChildItemViewHolder extends ChildViewHolder {

    private TextView tvChild;
    private ChildItemClickedListener mListener;

    public ChildItemViewHolder(View itemView, final ChildItemClickedListener mListener) {
        super(itemView);
        this.mListener = mListener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onChildItemClicked(getAdapterPosition());
            }
        });

        tvChild = itemView.findViewById(R.id.tv_child);
    }

    public void bindData(ChildModel childModel) {
        tvChild.setText(childModel.getChildName());
    }

    public interface ChildItemClickedListener {
        void onChildItemClicked(int position);
    }
}
