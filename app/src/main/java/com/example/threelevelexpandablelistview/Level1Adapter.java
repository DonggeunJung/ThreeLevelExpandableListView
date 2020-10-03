package com.example.threelevelexpandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

public class Level1Adapter extends BaseExpandableListAdapter {

    private Context context;
    private List<RowData> arrData;

    public Level1Adapter(Context context, List<RowData> arrData,
                         OnClickListener onClickListener) {
        this.context = context;
        this.arrData = arrData;
        this.onClickListener = onClickListener;
    }

    @Override
    public Object getChild(int arg0, int arg1) {
        return arg1;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SecondLevelExpandableListView secondLevelELV =
                new SecondLevelExpandableListView(context);
        Level2Adapter level2Adapter = new Level2Adapter(context, groupPosition+1, childPosition+1);
        secondLevelELV.setAdapter(level2Adapter);
        secondLevelELV.setGroupIndicator(null);
        secondLevelELV.setOnChildClickListener(level2Adapter.onChildClickListener);
        return secondLevelELV;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return getRowCount(groupPosition + 1);
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupCount() {
        return getRowCount();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_first, null);
            TextView text = convertView.findViewById(R.id.eventsListEventRowText);
            RowData rowData = getRowData(groupPosition + 1);
            if(rowData != null) {
                text.setText(rowData.text);
            }
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private int getRowCount() {
        int count = 0;
        for(RowData rowData: arrData) {
            if(rowData.level1 != 0 && rowData.level2 == 0 && rowData.level3 == 0) {
                count ++;
            }
        }
        return count;
    }

    private RowData getRowData(int level1) {
        for(RowData rowData: arrData) {
            if(rowData.level1 == level1 && rowData.level2 == 0 && rowData.level3 == 0) {
                return rowData;
            }
        }
        return null;
    }

    private int getRowCount(int level1) {
        int count = 0;
        for(RowData rowData: arrData) {
            if(rowData.level1 == level1 && rowData.level2 != 0 && rowData.level3 == 0) {
                count ++;
            }
        }
        return count;
    }

    private RowData getRowData(int level1, int level2) {
        for(RowData rowData: arrData) {
            if(rowData.level1 == level1 && rowData.level2 == level2 && rowData.level3 == 0) {
                return rowData;
            }
        }
        return null;
    }

    private int getRowCount(int level1, int level2) {
        int count = 0;
        for(RowData rowData: arrData) {
            if(rowData.level1 == level1 && rowData.level2 == level2 && rowData.level3 != 0) {
                count ++;
            }
        }
        return count;
    }

    private RowData getRowData(int level1, int level2, int level3) {
        for(RowData rowData: arrData) {
            if(rowData.level1 == level1 && rowData.level2 == level2 && rowData.level3 == level3) {
                return rowData;
            }
        }
        return null;
    }


    public class SecondLevelExpandableListView extends ExpandableListView {

        public SecondLevelExpandableListView(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public class Level2Adapter extends BaseExpandableListAdapter {

        private Context context;
        private int level1;
        private int level2;

        public Level2Adapter(Context context, int level1, int level2) {
            this.context = context;
            this.level1 = level1;
            this.level2 = level2;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return 1;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_second, null);
                TextView text = convertView.findViewById(R.id.eventsListEventRowText);
                RowData rowData = getRowData(level1, level2);
                text.setText(rowData.text);
            }
            return convertView;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_third, null);
                TextView text = convertView.findViewById(R.id.eventsListEventRowText);
                RowData rowData = getRowData(level1, level2, childPosition+1);
                text.setText(rowData.text);
            }
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return getRowCount(level1, level2);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public ExpandableListView.OnChildClickListener onChildClickListener = new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                if(onClickListener != null) {
                    onClickListener.onClick(level1, level2, childPosition+1);
                }
                return true;
            }
        };
    }

    public static class RowData {
        public int level1 = 1;
        public int level2 = 1;
        public int level3 = 1;
        public String text = "Empty";

        RowData(int level1, int level2, int level3, String text) {
            this.level1 = level1;
            this.level2 = level2;
            this.level3 = level3;
            this.text = text;
        }
    }

    private OnClickListener onClickListener = null;

    /*public static void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }*/

    public interface OnClickListener {
        void onClick(int level1, int level2, int level3) ;
    }
}
