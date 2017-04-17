package com.shall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaurav.cdsrecyclerview.CdsRecyclerViewAdapter;
import com.shall.R;
import com.shall.pojo.Courses;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed S. El-Shall on 4/8/2017.
 */

public class CompletedCoursesAdapter extends CdsRecyclerViewAdapter<Courses.DataBean.CourseBean, CompletedCoursesAdapter.ViewHolder> {

    private Context mContext;

    public CompletedCoursesAdapter(Context context, List<Courses.DataBean.CourseBean> list) {
        super(context, list);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list_courses, parent, false));
    }

    //Cast the viewholder to your custom view holder and then use it
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder = (ViewHolder) holder;
        Courses.DataBean.CourseBean course = getList().get(position);
        ((ViewHolder) holder).tvCourseName.setText(course.getName());
        ((ViewHolder) holder).tvCourseDesc.setText(course.getDescription());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCourseName)
        TextView tvCourseName;

        @BindView(R.id.tvCourseDesc)
        TextView tvCourseDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
