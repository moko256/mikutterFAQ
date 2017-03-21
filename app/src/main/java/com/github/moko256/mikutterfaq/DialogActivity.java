package com.github.moko256.mikutterfaq;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.github.moko256.mikutterfaq.databinding.ActivityDialogBinding;

public class DialogActivity extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDialogBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dialog);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        if (getIntent() != null){

            String title = getIntent().getStringExtra("title");
            if (title != null){
                setTitle(title);
                actionBar.setTitle(title);
            }

            String text = getIntent().getStringExtra("text");
            if (text != null){
                binding.setText(text);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public static Intent getIntent(Context context, Question question){
        return new Intent(context, DialogActivity.class)
                .putExtra("title", question.getQuestion())
                .putExtra("text", question.getAnswer());
    }
}
