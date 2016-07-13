package com.example.y.mvp.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.example.y.mvp.R;
import com.example.y.mvp.utils.DiaLogUtils;
import com.example.y.mvp.utils.RxBusUtils;
import com.example.y.mvp.utils.RxUtil;
import com.example.y.mvp.utils.theme.ReplaceThemeUtils;
import com.example.y.mvp.utils.theme.SharedPreferencesMgr;

import java.util.List;


/**
 * by 12406 on 2016/6/16.
 */
public class MenuItemAdapter extends BaseRecyclerViewAdapter<String>
        implements RxUtil.RxBinding, RxBusUtils.RxBusTheme {


    private ImageView imageView;

    public MenuItemAdapter(List<String> mDatas) {
        super(mDatas);
    }

    @Override
    protected void getHeadLayoutId(View headView) {
        super.getHeadLayoutId(headView);
        imageView = getView(headView, R.id.iv);
        ImageView headImage = getView(headView, R.id.head_image);
        RxUtil.clicks(imageView, this);
        if (SharedPreferencesMgr.getIsNight()) {
            setDay();
        } else {
            setNight();
        }
        RxBusUtils.rxTheme(this);
        headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaLogUtils.clearSql();
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.menu_item;
    }

    @Override
    protected void onBind(ViewHolder holder, int position, String data) {
        holder.setTextView(R.id.tv_menu_item, data);
    }

    @Override
    public void clicks() {
        ReplaceThemeUtils.theme((Activity) context);
    }

    @Override
    public void setDay() {
        imageView.setBackgroundResource(R.drawable.day);
    }

    @Override
    public void setNight() {
        imageView.setBackgroundResource(R.drawable.night);
    }
}
