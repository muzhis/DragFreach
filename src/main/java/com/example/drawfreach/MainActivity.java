package com.example.headerfreach;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String[] NAMES = new String[]{"A", "B", "C",
            "D", "E", "F", "G", "H", "I", "G", "K", "L", "M",
            "N", "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "G", "K", "L", "M",
            "N",};
    @BindView(R.id.listview)
    MyListview listview;
    private ImageView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        View view = View.inflate(this, R.layout.layout_header, null);
        header = (ImageView) view.findViewById(R.id.header);
        listview.addHeaderView(view);
        listview.setImageView(header);
        listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,NAMES));
    }
}
