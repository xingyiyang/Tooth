package com.scu.xing.tooth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner sp_tengtong1;  //疼痛第一级
    private Spinner sp_tengtong2;  //疼痛第二级
    private EditText editText_tengtong;  //疼痛的关键字
    private Spinner sp_jiancha;          //检查第一级
    private Spinner sp_jianchawaixing;   //检查第二级 外形
    private Spinner sp_jianchaqita;      //检查第二级 其他
    private EditText editText_jiancha;   //检查的关键字
    Bundle bundle = new Bundle();        //把选中的参数传到第二页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //设置页面的顶端
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_tool_bar);
        setSupportActionBar(toolbar);

        //初始化疼痛第一级列表，监听列表
        sp_tengtong1 = (Spinner)findViewById(R.id.spinner_tengtong1);
        sp_tengtong1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //如果选择的是“激发痛+”，则展开第二级列表，否则隐藏第二级列表
                if(i==1){
                    sp_tengtong2.setVisibility(View.VISIBLE);
                }else{
                    sp_tengtong2.setVisibility(View.INVISIBLE);
                }
                //获取spinner中选中的字符串
                String tengtong1 = (String)sp_tengtong1.getSelectedItem();
                //把字符串保存到bundle中，相当于保存在map里面
                bundle.putString("tengtongfirst",tengtong1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //初始化疼痛第二级列表，监听列表
        sp_tengtong2 = (Spinner)findViewById(R.id.spinner_tengtong2);
        sp_tengtong2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tengtong2 = (String)sp_tengtong2.getSelectedItem();
                bundle.putString("tengtongsecond",tengtong2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //疼痛的关键字
        editText_tengtong = (EditText)findViewById(R.id.edt_tengtong);

        //初始化检查第一级列表，监听列表
        sp_jiancha = (Spinner)findViewById(R.id.spinner_jiancha);
        sp_jiancha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    sp_jianchawaixing.setVisibility(View.VISIBLE);
                    sp_jianchaqita.setVisibility(View.GONE);
                }else if(i == 1){
                    sp_jianchawaixing.setVisibility(View.GONE);
                    sp_jianchaqita.setVisibility(View.VISIBLE);
                }else{
                    sp_jianchawaixing.setVisibility(View.VISIBLE);
                    sp_jianchaqita.setVisibility(View.GONE);
                }
                String str_jiancha = (String)sp_jiancha.getSelectedItem();
                bundle.putString("jiancha",str_jiancha);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //初始化检查第二级列表，外形，监听列表
        sp_jianchawaixing = (Spinner)findViewById(R.id.spinner_jianchawaixing);
        sp_jianchawaixing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str_waixing = (String)sp_jianchawaixing.getSelectedItem();
                bundle.putString("waixing",str_waixing);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //初始化检查第二级列表，其他，监听列表
        sp_jianchaqita = (Spinner)findViewById(R.id.spinner_jianchaqita);
        sp_jianchaqita.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str_qita = (String)sp_jianchaqita.getSelectedItem();
                bundle.putString("qita",str_qita);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //检查的关键字
        editText_jiancha = (EditText)findViewById(R.id.edt_jiancha);

        //查询的监听
        findViewById(R.id.button_chaxuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp_tengtong1.getSelectedItemPosition()!=0){

                    getKeyword();
                    Toast.makeText(MainActivity.this,"查询",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,ListActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this,"请选择疼痛和检查类型",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.reset) {
            Toast.makeText(MainActivity.this,"重置",Toast.LENGTH_SHORT).show();
            closeAllSpinner();

        }
        return super.onOptionsItemSelected(item);
    }

    //重置选项，隐藏spinner，关闭toggleButton
    public void closeAllSpinner(){

        sp_tengtong1.setSelection(0);
        sp_tengtong2.setSelection(0);
        sp_jiancha.setSelection(0);
        sp_jianchawaixing.setSelection(0);
        sp_jianchaqita.setSelection(0);
    }

    //获取输入的关键字
    public void getKeyword(){
        if(editText_tengtong.getText()!=null){
            bundle.putString("tengtongkeyword",editText_tengtong.getText().toString());
        }
        if(editText_jiancha.getText()!=null){
            bundle.putString("jianchakeyword",editText_jiancha.getText().toString());
        }
    }

    /**
     * 自定义spinner里面的item样式
     */
//    private class SpinnerAdapter extends ArrayAdapter<String> {
//        Context context;
//        String[] items = new String[] {};
//
//        public SpinnerAdapter(final Context context,
//                              final int textViewResourceId, final String[] objects) {
//            super(context, textViewResourceId, objects);
//            this.items = objects;
//            this.context = context;
//        }
//
//        @Override
//        public View getDropDownView(int position, View convertView,
//                                    ViewGroup parent) {
//
//            if (convertView == null) {
//                LayoutInflater inflater = LayoutInflater.from(context);
//                convertView = inflater.inflate(
//                        android.R.layout.simple_spinner_item, parent, false);
//            }
//
//            TextView tv = (TextView) convertView
//                    .findViewById(android.R.id.text1);
//            tv.setText(items[position]);
//            return convertView;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                LayoutInflater inflater = LayoutInflater.from(context);
//                convertView = inflater.inflate(
//                        android.R.layout.simple_spinner_item, parent, false);
//            }
//
//            // android.R.id.text1 is default text view in resource of the android.
//            // android.R.layout.simple_spinner_item is default layout in resources of android.
//
//            TextView tv = (TextView) convertView
//                    .findViewById(android.R.id.text1);
//            tv.setText(items[position]);
//            return convertView;
//        }
//    }
}
