package net.lzzy.algorithm;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Integer[] items;
    private EditText edtItems;
    private TextView tvResult;
    private LinearLayout container;

    Spinner spinner,initSearch;
    private Button btnSort;
    private View.OnClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.activity_main_tv_result);
        initSpinner();
        initSearch();
        initViews();
    }
    @SuppressLint("WrongViewCast")
    private void initSearch() {
        spinner=findViewById(R.id.activity_main_btn_sp2);
        spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,SearchFactory.getSortNames()));
        container=findViewById(R.id.Lin);
        findViewById(R.id.activity_main_btn_sp2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseSearch<Integer>search=
                        SearchFactory.getInstance(spinner.getSelectedItemPosition(),items);
                if (search!=null){
                    int pos=search.search(v.getId());
                    tvResult.setText("该元素位于数组的第".concat((pos+1)+"位"));
                }

            }
        });

    }
    private void resetSearch(){
        container.removeAllViews();
        generateItems();
        btnSort.callOnClick();
        for (Integer i:items){
            Button btn=new Button(this);
            btn.setText(String.format(i.toString(), Locale.CHINA));
            btn.setId(i);
            btn.setLayoutParams(new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,1));
            // btn.setOnClickListener(listener);
            btn.setOnClickListener(listener);
            container.addView(btn);
        }

    }
    private void initSpinner(){
        spinner=findViewById(R.id.activity_main_btn_sp);
        spinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.activity_list_item,SortFactory.getSortNames()));
    }
    private void initViews(){
        edtItems = findViewById(R.id.activity_main_edt_items);
        findViewById(R.id.activity_main_btn_generate).setOnClickListener(this);
        findViewById(R.id.activity_main_btn_sort).setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_btn_generate:
                generateItems();
                displayItems(edtItems);
                break;
            case R.id.activity_main_btn_sort:
                BaseSort<Integer>sort=SortFactory.getInstance(spinner.getSelectedItemPosition(),items);
                BaseSort<Integer> sortNotNull= Objects.requireNonNull(sort);
                sortNotNull.sortwithTime();
                String result=sort.getResult();
                tvResult.setText(result);
                Toast.makeText(this, "总时长："+sort.getDuration(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    private void displayItems(TextView tv) {
        String display = "";
        for (Integer i : items) {
            display = display.concat(i + ",");
        }
        display = display.substring(0, display.length() - 1);
        tv.setText(display);
    }

    private void directSort() {
        //todo:直接选择排序的具体实现
        //分为有序区和无序区，每一趟都在无序区依次对比，记录对比区域的最小元素的位置
        //然后把无序区第一个元素和所记录的最小元素进行交换，无序区少一个、有序区多一个，循环往复直至无序区
        //元素数量为0
        for (int i=0;i<items.length-1;i++){
            int minPos=i;
            for (int j=i+1;j<items.length;j++){
                if (items[minPos].compareTo(items[j])>0){
                    minPos=j;
                }
            }
            swap(minPos,i);
        }
    }
    private void insettSort(){

        for (int i=1;i<items.length;i++){
            int j=i-1;
            if (items[j].compareTo(items[i])<0){
                continue;
            }
            Integer tmp=items[i];
            while (j>=0&&items[i].compareTo(tmp)>0){
                items[j+1]=items[j];
                j--;
            }
            items[j+i]=tmp;
        }
    }
    private void swap(int m, int n) {
        int tmp=items[m];
        items[m]=items[n];
        items[n]=tmp;
    }
    private void generateItems() {
        items = new Integer[10];
        Random generator = new Random();
        for (int i = 0; i < items.length; i++) {
            items[i] = generator.nextInt(99);
        }
    }
}