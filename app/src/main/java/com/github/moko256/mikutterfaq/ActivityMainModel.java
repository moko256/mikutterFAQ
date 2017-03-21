package com.github.moko256.mikutterfaq;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.LayoutInflater;

import java.util.ArrayList;

/**
 * Created by moko256 on 2017/03/21.
 */

public class ActivityMainModel extends BaseObservable {

    private GetQuestionsModule module;
    private ListAdapter adapter;
    private ArrayList<Question> questions = new ArrayList<>();

    private boolean loading;

    public ActivityMainModel(LayoutInflater inflater, GetQuestionsModule module){
        setAdapter(new ListAdapter(inflater, questions));
        this.module = module;
    }

    public void updateQuestions(){
        setLoading(true);
        module.getQuestionsObservable().subscribe(
                it->{
                    questions.clear();
                    questions.addAll(it);
                    adapter.notifyDataSetChanged();
                    setLoading(false);
                },
                throwable -> {
                    setLoading(false);
                    throwable.printStackTrace();
                    onError(throwable);
                },
                ()->{

                }
        );
    }

    public void onError(Throwable e){

    }

    public ListAdapter.OnListItemClickLisener getOnListChildClickListener() {
        return adapter.getOnListItemClickLisener();
    }

    public void setOnListChildClickListener(ListAdapter.OnListItemClickLisener onListChildClickListener) {
        adapter.setOnListItemClickLisener(onListChildClickListener);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    @Bindable
    public boolean isLoading() {
        return loading;
    }

    private void setLoading(boolean loading) {
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }

    @Bindable
    public ListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ListAdapter adapter) {
        this.adapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
