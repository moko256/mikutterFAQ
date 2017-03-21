package com.github.moko256.mikutterfaq;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by moko256 on 2017/03/21.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Question> questionList;

    private OnListItemClickLisener onListItemClickLisener;

    public ListAdapter(LayoutInflater inflater, List<Question> list){
        layoutInflater = inflater;
        questionList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.layout_list_child, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.binding.setVariable(
                BR.M,
                new LayoutListChildModel(
                        question.getQuestion(),
                        view->{
                            onListItemClickLisener.onClick(question);
                        }
                )
        );
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding binding;

        ViewHolder(ViewDataBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public OnListItemClickLisener getOnListItemClickLisener() {
        return onListItemClickLisener;
    }

    public void setOnListItemClickLisener(OnListItemClickLisener onListItemClickLisener) {
        this.onListItemClickLisener = onListItemClickLisener;
    }

    public interface OnListItemClickLisener{
        void onClick(Question question);
    }
}
