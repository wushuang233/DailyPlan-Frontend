package com.example.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FocusActivity extends AppCompatActivity {

    private Button button60;
    private Button button40;
    private Button button30;
    private Button button25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_focus);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(FocusActivity.this, HomeActivity.class));
                return true;
            } else if (itemId == R.id.navigation_focus) {
//                startActivity(new Intent(FocusActivity.this, FocusActivity.class));
                return true;
            } else if (itemId == R.id.navigation_mine) {
                startActivity(new Intent(FocusActivity.this, MineActivity.class));
                return true;
            }
            return false;
        });
        button60 = findViewById(R.id.radio_button_60min);
        button40 = findViewById(R.id.radio_button_40min);
        button30 = findViewById(R.id.radio_button_30min);
        button25 = findViewById(R.id.radio_button_25min);
        Button newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个对话框构建器
                AlertDialog.Builder builder = new AlertDialog.Builder(FocusActivity.this);
                builder.setTitle("设置专注时间");

                // 设置对话框的布局
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_set_timer, null);
                builder.setView(dialogView);

                // 获取对话框中的 EditText
                EditText timerEditText = dialogView.findViewById(R.id.timer_edit_text);

                // 设置对话框按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取用户输入的时间
                        String timeStr = timerEditText.getText().toString();
                        int time = Integer.parseInt(timeStr);
                        showDialogWithTimer(time);
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 用户取消设置，不进行任何操作
                    }
                });

                // 创建并显示对话框
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        button60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithTimer(60);
            }
        });

        button40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithTimer(40);
            }
        });

        button30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithTimer(30);
            }
        });

        button25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithTimer(25);
            }
        });
    }

    private void showDialogWithTimer(final int minutes) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.countdown_dialog_layout);

        final TextView countdownTimer = dialog.findViewById(R.id.countdown_timer);
        Button closeButton = dialog.findViewById(R.id.close_button);
        dialog.setCanceledOnTouchOutside(false);

        // 设置倒计时
        new CountDownTimer(minutes * 60000, 1000) {
            public void onTick(long millisUntilFinished) {
                long minutesLeft = millisUntilFinished / 60000;
                long secondsLeft = (millisUntilFinished % 60000) / 1000;
                countdownTimer.setText(String.format("%02d:%02d", minutesLeft, secondsLeft));
            }

            public void onFinish() {
                countdownTimer.setText("小主~专注结束啦");
            }
        }.start();

        // 设置关闭按钮点击事件
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // 关闭弹窗
            }
        });

        // 显示弹窗
        dialog.show();
    }

}