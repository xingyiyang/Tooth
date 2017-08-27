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
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 主页面，用于查询的输入
 * Created by xing on 2017/8/14.
 */
public class MainActivity extends AppCompatActivity {

    Spinner sp_tengtong1;  //第一个疼痛第一级
    Spinner sp_tengtong2;  //第一个疼痛第二级
    Spinner sp_tt_blank;   //第一个疼痛空列表

    Spinner sp_tengtong11;  //第二个疼痛第一级
    Spinner sp_tengtong12;  //第二个疼痛第二级
    Spinner sp_tt_blank2;   //第二个疼痛空列表


    Spinner sp_jiancha;          //第一个检查第一级
    Spinner sp_jianchawaixing;   //第一个检查第二级 外形
    Spinner sp_jianchaqita;      //第一个检查第二级 其他
    Spinner sp_jc_blank;         //第一个检查空列表

    Spinner sp_jiancha2;          //第二个检查第一级
    Spinner sp_jianchawaixing2;   //第二个检查第二级 外形
    Spinner sp_jianchaqita2;      //第二个检查第二级 其他
    Spinner sp_jc_blank2;         //第二个检查空列表

    Bundle bundle;        //把选中的参数传到第二页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //初始化控件
        initWidget();

        //设置页面的顶端
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_tool_bar);
        setSupportActionBar(toolbar);

        //第一组疼痛开始
        //监听疼痛第一级列表
        sp_tengtong1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //如果选择的是“激发痛+”，则展开第二级列表，否则隐藏第二级列表
                if(i==1){
                    sp_tengtong2.setVisibility(View.VISIBLE);
                    sp_tt_blank.setVisibility(View.GONE);
                }else{
                    sp_tengtong2.setVisibility(View.GONE);
                    sp_tt_blank.setVisibility(View.VISIBLE);
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

        //监听疼痛第二级列表，"激发痛+"的第二级列表
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
        //第一组疼痛结束

        //第二组疼痛开始
        //监听疼痛第一级列表
        sp_tengtong11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //如果选择的是“激发痛+”，则展开第二级列表，否则隐藏第二级列表
                if(i==1){
                    sp_tengtong12.setVisibility(View.VISIBLE);
                    sp_tt_blank2.setVisibility(View.GONE);
                }else{
                    sp_tengtong12.setVisibility(View.GONE);
                    sp_tt_blank2.setVisibility(View.VISIBLE);
                }

                //获取spinner中选中的字符串
                String tengtong2 = (String)sp_tengtong11.getSelectedItem();
                //把字符串保存到bundle中，相当于保存在map里面
                bundle.putString("tengtongfirst2",tengtong2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //监听疼痛第二级列表，"激发痛+"的第二级列表
        sp_tengtong12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String tengtong12 = (String)sp_tengtong12.getSelectedItem();
                bundle.putString("tengtongsecond2",tengtong12);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //第二组疼痛结束


        //第一组检查开始
        //监听检查第一级列表
        sp_jiancha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==1){
                    sp_jianchawaixing.setVisibility(View.VISIBLE);
                    sp_jianchaqita.setVisibility(View.GONE);
                    sp_jc_blank.setVisibility(View.GONE);
                }else if(i == 2){
                    sp_jianchaqita.setVisibility(View.VISIBLE);
                    sp_jianchawaixing.setVisibility(View.GONE);
                    sp_jc_blank.setVisibility(View.GONE);
                }else{
                    sp_jc_blank.setVisibility(View.VISIBLE);
                    sp_jianchawaixing.setVisibility(View.GONE);
                    sp_jianchaqita.setVisibility(View.GONE);
                }

                String str_jiancha = (String)sp_jiancha.getSelectedItem();
                bundle.putString("jiancha",str_jiancha);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //监听检查第二级列表，外形
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

        //监听检查第二级列表，其他
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
        //第一组检查结束

        //第二组检查开始
        //监听检查第一级列表
        sp_jiancha2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==1){
                    sp_jianchawaixing2.setVisibility(View.VISIBLE);
                    sp_jianchaqita2.setVisibility(View.GONE);
                    sp_jc_blank2.setVisibility(View.GONE);
                }else if(i == 2){
                    sp_jianchaqita2.setVisibility(View.VISIBLE);
                    sp_jianchawaixing2.setVisibility(View.GONE);
                    sp_jc_blank2.setVisibility(View.GONE);
                }else{
                    sp_jc_blank2.setVisibility(View.VISIBLE);
                    sp_jianchawaixing2.setVisibility(View.GONE);
                    sp_jianchaqita2.setVisibility(View.GONE);
                }

                String str_jiancha2 = (String)sp_jiancha2.getSelectedItem();
                bundle.putString("jiancha2",str_jiancha2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //监听检查第二级列表，外形
        sp_jianchawaixing2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String str_waixing2 = (String)sp_jianchawaixing2.getSelectedItem();
                bundle.putString("waixing2",str_waixing2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //监听检查第二级列表，其他
        sp_jianchaqita2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String str_qita2 = (String)sp_jianchaqita2.getSelectedItem();
                bundle.putString("qita2",str_qita2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //第二组检查结束

        //查询按钮监听
        findViewById(R.id.button_chaxuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp_tengtong1.getSelectedItemPosition()!=0){
                    if(sp_jiancha.getSelectedItemPosition()!=0){

                        Intent intent = new Intent(MainActivity.this,ListActivity.class);
                        //把参数传递到第二个页面
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"请选择检查类型",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"请选择疼痛类型",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 设置顶部的标题栏
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * 监听重置按钮
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.reset) {
            Toast.makeText(MainActivity.this,"重置",Toast.LENGTH_SHORT).show();
            resetAll();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 重置按钮，下拉列表重置
     */
    public void resetAll(){

        //疼痛重置
        sp_tengtong1.setSelection(0);
        sp_tengtong2.setSelection(0);
        sp_tengtong11.setSelection(0);
        sp_tengtong12.setSelection(0);

        //检查重置
        sp_jiancha.setSelection(0);
        sp_jianchawaixing.setSelection(0);
        sp_jianchaqita.setSelection(0);
        sp_jiancha2.setSelection(0);
        sp_jianchawaixing2.setSelection(0);
        sp_jianchaqita2.setSelection(0);

    }

    /**
     * 初始化控件
     */
    public void initWidget(){

        //第一组疼痛
        sp_tengtong1 = (Spinner)findViewById(R.id.spinner_tengtong1);  //疼痛第一级
        sp_tengtong2 = (Spinner)findViewById(R.id.spinner_tengtong2);  //疼痛第二级
        sp_tt_blank = (Spinner)findViewById(R.id.spinner_tt_blank);    //疼痛空

        //第二组疼痛
        sp_tengtong11 = (Spinner)findViewById(R.id.spinner_tengtong11);  //疼痛第一级
        sp_tengtong12 = (Spinner)findViewById(R.id.spinner_tengtong12);  //疼痛第二级
        sp_tt_blank2 = (Spinner)findViewById(R.id.spinner_tt_blank2);    //疼痛空

        //第一组检查
        sp_jiancha = (Spinner)findViewById(R.id.spinner_jiancha);      //检查第一级
        sp_jianchawaixing = (Spinner)findViewById(R.id.spinner_jianchawaixing);  //检查第二级 外形
        sp_jianchaqita = (Spinner)findViewById(R.id.spinner_jianchaqita);        //检查第二级 其他
        sp_jc_blank = (Spinner)findViewById(R.id.spinner_jc_blank);    //检查空列表

        //第二组检查
        sp_jiancha2 = (Spinner)findViewById(R.id.spinner_jiancha2);      //检查第一级
        sp_jianchawaixing2 = (Spinner)findViewById(R.id.spinner_jianchawaixing2);  //检查第二级 外形
        sp_jianchaqita2 = (Spinner)findViewById(R.id.spinner_jianchaqita2);        //检查第二级 其他
        sp_jc_blank2 = (Spinner)findViewById(R.id.spinner_jc_blank2);    //检查空列表

        bundle = new Bundle();  //键值对bundle
    }

}
