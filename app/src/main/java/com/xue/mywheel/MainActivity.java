package com.xue.mywheel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.xue.mywheel.library.adapter.ScrollPickerAdapter;
import com.xue.mywheel.library.view.ScrollPickerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ConstraintLayout mainLayout;
    PopupWindow pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout=findViewById(R.id.main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortPop();
            }
        });
    }
    private void sortPop(){
        View view = LayoutInflater.from(this).inflate(R.layout.pop_sort,null);
        ScrollPickerView mScrollPickerView=view.findViewById(R.id.pick_pop_sort);
        mScrollPickerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        list.add("默认排序");
        list.add("按加入时间正序");
        list.add("按加入时间倒序");
        list.add("按最近联系时间正序");
        list.add("按最近联系时间倒序");

        ScrollPickerAdapter.ScrollPickerAdapterBuilder<String> builder =
                new ScrollPickerAdapter.ScrollPickerAdapterBuilder<String>(this)
                        .setDataList(list)
                        .selectedItemOffset(2)
                        .visibleItemNumber(3)
                        .setDivideLineColor("#ffffff")
                        .setItemViewProvider(null)
                        .setOnScrolledListener(new ScrollPickerAdapter.OnScrollListener() {
                            @Override
                            public void onScrolled(View currentItemView) {
                                String sortText = (String) currentItemView.getTag();
                                if (sortText != null) {
                                    Toast.makeText(MainActivity.this, sortText, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
        ScrollPickerAdapter mScrollPickerAdapter = builder.build();
        mScrollPickerView.setAdapter(mScrollPickerAdapter);

        Button sure= view.findViewById(R.id.sure_pop_sort);
        Button cancle= view.findViewById(R.id.cancle_pop_sort);

        sure.setOnClickListener(this);
        cancle.setOnClickListener(this);

        pop= new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Window window =getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.alpha = 0.4f;//1.０全透明．０不透明．
        window.setAttributes(windowParams);
        pop.setOutsideTouchable(false);
        pop.setFocusable(true);
        pop.showAtLocation(mainLayout, Gravity.BOTTOM,0,0);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Window window = getWindow();
                WindowManager.LayoutParams windowParams = window.getAttributes();
                windowParams.alpha = 1.0f;//1.０全透明．０不透明．
                window.setAttributes(windowParams);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sure_pop_sort:
                //根据选择的排序更新数据

                pop.dismiss();
                break;
            case R.id.cancle_pop_sort:
                pop.dismiss();
                break;
        }
    }
}
