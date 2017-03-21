package com.github.moko256.mikutterfaq;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.moko256.mikutterfaq.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainModel model = new ActivityMainModel(getLayoutInflater(), new HttpGetQuestionsModule()){
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_LONG).show();
            }
        };
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLoading(model.isLoading());
        binding.setAdapter(model.getAdapter());
        model.setOnListChildClickListener(question -> startActivity(DialogActivity.getIntent(this, question)));
        model.updateQuestions();
    }
}