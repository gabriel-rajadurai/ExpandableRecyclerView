package com.gabriel.expandablerecyclerview_customized;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gabriel.expandablerecyclerview_customized.models.ChildModel;
import com.gabriel.expandablerecyclerview_customized.models.GroupModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<GroupModel> groupModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        setUpExpandableList();
    }

    private void setUpExpandableList() {

        groupModels = getData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
        ));

        recyclerView.setAdapter(new ExpandableAdapter2(this, groupModels, true));
    }

    private List<GroupModel> getData() {

        groupModels = new ArrayList<>();

        //Group A
        List<ChildModel> childModels1 = new ArrayList<>();
        childModels1.add(new ChildModel("A1", "1"));
        childModels1.add(new ChildModel("A2", "2"));
        childModels1.add(new ChildModel("A3", "3"));
        childModels1.add(new ChildModel("A4", "4"));

        groupModels.add(new GroupModel("Group A", childModels1));

        //Group B
        List<ChildModel> childModels2 = new ArrayList<>();
        childModels2.add(new ChildModel("B1", "1"));
        childModels2.add(new ChildModel("B2", "2"));
        childModels2.add(new ChildModel("B3", "3"));
        childModels2.add(new ChildModel("B4", "4"));
        childModels2.add(new ChildModel("B5", "5"));
        childModels2.add(new ChildModel("B6", "6"));
        childModels2.add(new ChildModel("B7", "7"));
        childModels2.add(new ChildModel("B8", "8"));
        childModels2.add(new ChildModel("B9", "9"));

        groupModels.add(new GroupModel("Group B", childModels2));

        //Group C
        List<ChildModel> childModels3 = new ArrayList<>();
        childModels3.add(new ChildModel("C1", "1"));
        childModels3.add(new ChildModel("C2", "2"));
        childModels3.add(new ChildModel("C3", "3"));

        groupModels.add(new GroupModel("Group C", childModels3));

        //Group D
        List<ChildModel> childModels4 = new ArrayList<>();
        childModels4.add(new ChildModel("D1", "1"));
        childModels4.add(new ChildModel("D2", "2"));
        childModels4.add(new ChildModel("D3", "3"));
        childModels4.add(new ChildModel("D4", "4"));
        childModels4.add(new ChildModel("D5", "5"));
        childModels4.add(new ChildModel("D6", "6"));

        groupModels.add(new GroupModel("Group D", childModels4));

        return groupModels;
    }
}
