package com.zhizhen.ybb.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.psylife.wrmvplibrary.utils.StatusBarUtil;
import com.psylife.wrmvplibrary.utils.TitleBuilder;
import com.zhizhen.ybb.R;
import com.zhizhen.ybb.base.YbBaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

/**
 * 设定系统时间
 * Created by sandlovechao on 2017/5/22.
 */

public class SetPosTureActivity extends YbBaseActivity {

    @BindView(R.id.txt_posture)
    TextView txtPosture;

    @Override
    public int getLayoutId() {
        return R.layout.avtivity_set_posture;
    }

    public void setStatusBarColor() {
        StatusBarUtil.setColor(this, this.getResources().getColor(R.color.blue_313245));
    }

    @Override
    public View getTitleView() {
        return new TitleBuilder(this).setLeftText(getString(R.string.set_posture))
                .setLeftImage(R.mipmap.tab_back)
                .setRightText(getString(R.string.complete))
                .setTitleBgRes(R.color.blue_313245)
                .setLeftOnClickListener(v -> finish())
                .setRightOnClickListener(v -> {
                    Intent intent = new Intent(this, EditDataActivity.class);
                    intent.putExtra("posture", txtPosture.getText().toString().trim());
                    this.setResult(ParameterSetActivity.SET_POSTURE, intent);
                    this.finish();
                })
                .build();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        txtPosture.setOnClickListener(v -> {
            showTime();
        });
    }

    @Override
    public void initdata() {
//        Intent bundle = this.getIntent();
//        txtPosture.setText(bundle.getStringExtra("posture"));
    }

    private void showTime() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            // 指定一个日期
            Date deTime = dateFormat.parse(txtPosture.getText().toString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(deTime);
            //时间选择器
            TimePickerView pvTime = new TimePickerView.Builder(this, (date, v1) -> {
                txtPosture.setText(dateFormat.format(date));
            })
                    .setType(new boolean[]{true, true, true, false, false, false})
                    .setTextColorCenter(this.getResources().getColor(R.color.blue_b3007aff))
                    .setTitleSize(R.dimen.txt_size)
                    .isCenterLabel(true)
                    .build();
            pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
            pvTime.show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}