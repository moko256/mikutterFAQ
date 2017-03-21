package com.github.moko256.mikutterfaq;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

/**
 * Created by moko256 on 2017/03/21.
 */

public class LayoutListChildModel extends BaseObservable {
    private String title;
    private View.OnClickListener onClickListener;

    public LayoutListChildModel(String title, View.OnClickListener onClickListener){
        this.title = title;
        this.onClickListener = onClickListener;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        notifyPropertyChanged(BR.onClickListener);
    }
}
