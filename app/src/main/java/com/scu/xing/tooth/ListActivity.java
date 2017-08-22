package com.scu.xing.tooth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by xing on 2017/8/14.
 */

public class ListActivity extends AppCompatActivity{

    private TextView textView_tengtong;
    private TextView textView_jiancha;
    private ListView listView;
    private Toolbar toolbar;
    private String[] data={"apple","banana","orange"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        toolbar = (Toolbar) findViewById(R.id.list_tool_bar);
        toolbar.setTitle("查询结果");
        setSupportActionBar(toolbar);

        //返回上一页
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //获取第一个页面参数的值，把参数的值展示出来
        initTextView();

        //设置listview的数据
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivity.this,android.R.layout.simple_list_item_1,data);
        listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        //监听listview列表中的item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = listView.getItemAtPosition(i).toString();
                Toast.makeText(ListActivity.this,str,Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取上一个页面查询的参数
    private void initTextView(){
        Bundle bundle = getIntent().getExtras();
        String tengtong1 = bundle.getString("tengtongfirst");
        String tengtong2 = bundle.getString("tengtongsecond");
        String jiancha = bundle.getString("jiancha");
        String waixing = bundle.getString("waixing");
        String qita = bundle.getString("qita");
        String tengtongkeyword = bundle.getString("tengtongkeyword");
        String jianchakeyword = bundle.getString("jianchakeyword");

        textView_tengtong = (TextView)findViewById(R.id.textView_tengtong);
        textView_jiancha = (TextView)findViewById(R.id.textView_jiancha);

        textView_tengtong.setText("疼痛类型：");
        if(tengtong1!=null){
            textView_tengtong.setText(textView_tengtong.getText()+tengtong1);
        }
        if(tengtong1.equals("激发痛+") && tengtong2!=null){
            textView_tengtong.setText(textView_tengtong.getText()+" / "+tengtong2);
        }
        if(tengtongkeyword!=null){
            textView_tengtong.setText(textView_tengtong.getText()+" / "+tengtongkeyword);
        }

        textView_jiancha.setText("检查类型：");
        if(jiancha.equals("外形")){
            textView_jiancha.setText(textView_jiancha.getText()+jiancha+" / "+waixing);
        }
        if(jiancha.equals("其他")){
            textView_jiancha.setText(textView_jiancha.getText()+jiancha+" / "+qita);
        }
        if(jianchakeyword!=null){
            textView_jiancha.setText(textView_jiancha.getText()+" / "+jianchakeyword);
        }

    }

}
