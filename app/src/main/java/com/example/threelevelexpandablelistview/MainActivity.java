package com.example.threelevelexpandablelistview;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MainActivity extends AppCompatActivity implements Level1Adapter.OnClickListener {

    private ExpandableListView expandableListView;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        expandableListView = findViewById(R.id.mainList);
        List<Level1Adapter.RowData> arr = makeDataList();
        expandableListView.setAdapter(new Level1Adapter(this, arr, this));
    }

    private List<Level1Adapter.RowData> makeDataList() {
        List<Level1Adapter.RowData> arr = new ArrayList<>();
        arr.add(new Level1Adapter.RowData(1,0,0,"Item 1"));
        arr.add(new Level1Adapter.RowData(1,1,0,"Item 1-1"));
        arr.add(new Level1Adapter.RowData(1,1,1,"Item 1-1-1"));
        arr.add(new Level1Adapter.RowData(1,1,2,"Item 1-1-2"));
        arr.add(new Level1Adapter.RowData(1,2,0,"Item 1-2"));
        arr.add(new Level1Adapter.RowData(1,2,1,"Item 1-2-1"));
        arr.add(new Level1Adapter.RowData(1,2,2,"Item 1-2-2"));

        arr.add(new Level1Adapter.RowData(2,0,0,"Item 2"));
        arr.add(new Level1Adapter.RowData(2,1,0,"Item 2-1"));
        arr.add(new Level1Adapter.RowData(2,1,1,"Item 2-1-1"));
        arr.add(new Level1Adapter.RowData(2,1,2,"Item 2-1-2"));
        arr.add(new Level1Adapter.RowData(2,2,0,"Item 2-2"));
        arr.add(new Level1Adapter.RowData(2,2,1,"Item 2-2-1"));
        arr.add(new Level1Adapter.RowData(2,2,2,"Item 2-2-2"));
        arr.add(new Level1Adapter.RowData(2,2,3,"Item 2-2-3"));
        arr.add(new Level1Adapter.RowData(2,3,0,"Item 2-3"));
        arr.add(new Level1Adapter.RowData(2,3,1,"Item 2-3-1"));
        arr.add(new Level1Adapter.RowData(2,3,2,"Item 2-3-2"));

        arr.add(new Level1Adapter.RowData(3,0,0,"Item 3"));
        arr.add(new Level1Adapter.RowData(3,1,0,"Item 3-1"));
        arr.add(new Level1Adapter.RowData(3,1,1,"Item 3-1-1"));
        arr.add(new Level1Adapter.RowData(3,1,2,"Item 3-1-2"));
        arr.add(new Level1Adapter.RowData(3,2,0,"Item 3-2"));
        arr.add(new Level1Adapter.RowData(3,2,1,"Item 3-2-1"));
        arr.add(new Level1Adapter.RowData(3,2,2,"Item 3-2-2"));
        arr.add(new Level1Adapter.RowData(3,2,3,"Item 3-2-3"));
        arr.add(new Level1Adapter.RowData(3,2,4,"Item 3-2-4"));

        return arr;
    }

    public void onClick(int level1, int level2, int level3) {
        tv.setText(String.format("Item selected : %d - %d - %d",
                level1, level3, level3));
    }

}