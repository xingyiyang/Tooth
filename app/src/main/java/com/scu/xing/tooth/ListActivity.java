package com.scu.xing.tooth;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.scu.xing.tooth.dbhelper.DBHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询结果展示页面
 * Created by xing on 2017/8/14.
 */

public class ListActivity extends AppCompatActivity{

    private TextView textView_tengtong;
    private TextView textView_jiancha;
    private ListView listView;
    private Toolbar toolbar;
    private ImageView imagenoresult;

    //使用自定义的DBHelper对数据操作
    DBHelper helper = new DBHelper(ListActivity.this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        //初始化控件
        initWidget();

        //只读，不能修改数据库
        SQLiteDatabase db = helper.getReadableDatabase();

        //设置标题栏
        toolbar.setTitle("查询结果");
        setSupportActionBar(toolbar);

        //返回上一页
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //查询所有数据
        //queryAll();

        //把第一个页面传过来的值拼接成字符串
        String param= initTextView();
        final Map<String,String> result=queryBykeyWord(db,param);
        //如果map是空，说明没有该症状，显示查无此症，否则展示查询列表
        if(result.isEmpty()){
            listView.setVisibility(View.GONE);
            imagenoresult.setVisibility(View.VISIBLE);

        }else{
            imagenoresult.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            //设置listview的数据
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivity.this,android.R.layout.simple_list_item_1,new ArrayList<>(result.keySet()));
            listView.setAdapter(adapter);

            //监听listview列表中的item
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String title = listView.getItemAtPosition(i).toString();
                    //Toast.makeText(ListActivity.this,str,Toast.LENGTH_SHORT).show();
                    showDialog(title,result);
                }
            });
        }


    }

    private void initWidget(){
        textView_tengtong = (TextView)findViewById(R.id.textView_tengtong);
        textView_jiancha = (TextView)findViewById(R.id.textView_jiancha);
        listView = (ListView)findViewById(R.id.list_view);
        imagenoresult = (ImageView)findViewById(R.id.imagenoresult);
        toolbar = (Toolbar) findViewById(R.id.list_tool_bar);
    }

    /**
     * 获取上一个页面查询的参数
     */
    private String initTextView(){

        Bundle bundle = getIntent().getExtras();

        String tengtong1 = bundle.getString("tengtongfirst");
        String tengtong2 = bundle.getString("tengtongsecond");
        String tengtong11 = bundle.getString("tengtongfirst2");
        String tengtong12 = bundle.getString("tengtongsecond2");

        String jiancha = bundle.getString("jiancha");
        String waixing = bundle.getString("waixing");
        String qita = bundle.getString("qita");

        String jiancha2 = bundle.getString("jiancha2");
        String waixing2 = bundle.getString("waixing2");
        String qita2 = bundle.getString("qita2");

        StringBuilder stringBuilder = new StringBuilder();

        //第一组疼痛
        textView_tengtong.setText("疼痛类型：");
        if(tengtong1.equals("激发痛+")){
            textView_tengtong.setText(textView_tengtong.getText()+tengtong1+" / "+tengtong2);
            stringBuilder.append("%"+tengtong1+"%");
            stringBuilder.append("%"+tengtong2+"%");
        }else {
            textView_tengtong.setText(textView_tengtong.getText()+tengtong1);
            stringBuilder.append("%"+tengtong1+"%");
        }

        //第二组疼痛
        if(!tengtong11.equals("选择疼痛类型")){
            if(tengtong11.equals("激发痛+")){
                textView_tengtong.setText(textView_tengtong.getText()+" / "+tengtong11+" / "+tengtong12);
                stringBuilder.append("%"+tengtong11+"%");
                stringBuilder.append("%"+tengtong12+"%");
            }else{
                textView_tengtong.setText(textView_tengtong.getText()+" / "+tengtong11);
                stringBuilder.append("%"+tengtong11+"%");
            }

        }

        //第一组检查
        textView_jiancha.setText("检查类型：");
        if(jiancha.equals("外形")){
            textView_jiancha.setText(textView_jiancha.getText()+waixing);
            stringBuilder.append("%"+waixing+"%");
        }else{
            textView_jiancha.setText(textView_jiancha.getText()+qita);
            stringBuilder.append("%"+qita+"%");
        }

        //第二组检查
        if(jiancha2.equals("外形")){
            textView_jiancha.setText(textView_jiancha.getText()+" / "+waixing2);
            stringBuilder.append("%"+waixing2+"%");
        }
        if (jiancha2.equals("其他")){
            textView_jiancha.setText(textView_jiancha.getText()+" / "+qita2);
            stringBuilder.append("%"+qita2+"%");
        }

        return stringBuilder.toString().trim();

    }

    /**
     * 使用数据库查询所有列表
     */
    public void queryAll(SQLiteDatabase db){
        //查询数据库中的数据
        Cursor c = db.rawQuery("select * from tooth_table",null);
        //遍历数据库中的数据
        if(c!=null){
            String[] clos = c.getColumnNames();
            while (c.moveToNext()){
                for(String columname:clos){
                    Log.i("info",columname+" : "+c.getString(c.getColumnIndex(columname)));
                }
            }
            c.close();
        }
        db.close();
    }

    /**
     * 根据关键字查询数据列表
     */
    /*public ArrayList<String> queryBykeyWord(SQLiteDatabase db,String param){

        ArrayList<String> result = new ArrayList<String>();
        Cursor c = db.query("tooth_table",null,"key like ?",new String[]{param},null,null,null);
        if(c!=null){
            while (c.moveToNext()){
                result.add(c.getString(c.getColumnIndex("name")));
            }
            c.close();
        }else{
            Toast.makeText(ListActivity.this,"查无此症",Toast.LENGTH_SHORT).show();
        }
        db.close();
        return result;
    }*/

    public Map<String,String> queryBykeyWord(SQLiteDatabase db,String param){

        Map<String,String> result = new LinkedHashMap<String,String>();
        Cursor c = db.query("tooth_table",null,"key like ?",new String[]{param},null,null,null);
        if(c!=null){
            while (c.moveToNext()){
                result.put(c.getString(c.getColumnIndex("name")),c.getString(c.getColumnIndex("diagnosis")));
            }
            c.close();
        }
        db.close();
        return result;
    }

    /**
     * 点击item弹出dialog
     */
    public void showDialog(String title,Map<String,String> map){

        String diagnosis = map.get(title);

        Builder builder = new Builder(this);
        builder.setTitle(title);
        builder.setIcon(R.mipmap.tooth);
        builder.setMessage(diagnosis);
        builder.setPositiveButton("确定", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        //设置确定按钮的字体颜色为蓝色
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);

//        try {
//            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
//            mAlert.setAccessible(true);
//            Object mAlertController = mAlert.get(dialog);
//            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
//            mMessage.setAccessible(true);
//            TextView mMessageView = (TextView) mMessage.get(mAlertController);
//            mMessageView.setTextColor(Color.BLUE);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
    }

}
