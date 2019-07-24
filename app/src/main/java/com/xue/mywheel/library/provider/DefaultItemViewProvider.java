package com.xue.mywheel.library.provider;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xue.mywheel.R;
import com.xue.mywheel.library.IViewProvider;

public class DefaultItemViewProvider implements IViewProvider<String> {
    @Override
    public int resLayout() {
        return R.layout.scroll_picker_default_item_layout;
    }

    @Override
    public void onBindView(@NonNull View view, @Nullable String text) {
        TextView tv = view.findViewById(R.id.tv_content);
        tv.setText(text);
        view.setTag(text);
        tv.setTextSize(11);
    }

    @Override
    public void updateView(@NonNull View itemView, boolean isSelected) {
        TextView tv = itemView.findViewById(R.id.tv_content);
        tv.setTextSize(isSelected ? 11 : 10);
        tv.setTextColor(Color.parseColor(isSelected ? "#1E88F5" : "#A6A6A6"));
    }
}
