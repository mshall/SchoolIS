package com.shall.user.courses;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaurav.cdsrecyclerview.CdsRecyclerView;
import com.shall.MainActivity;
import com.shall.R;
import com.shall.adapters.RemainingCoursesAdapter;
import com.shall.LoginActivity;
import com.shall.network.App;
import com.shall.network.Controller;
import com.shall.pojo.Courses;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisteredCoursesFragment extends Fragment {


    public static final String TAG = RegisteredCoursesFragment.class.getName();
    @BindView(R.id.recyclerView)
    CdsRecyclerView recyclerView;
    View view;
    @Inject
    Retrofit retrofit;
    ArrayList<Courses.DataBean.CourseBean> courses;
    RemainingCoursesAdapter adapter;
    MainActivity activity;

    public RegisteredCoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courses = new ArrayList<>();
        activity = (MainActivity) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registered_courses, container, false);
        ButterKnife.bind(this, view);
        ((App) activity.getApplication()).getNetComponent().inject(this);
        initializeViews();
        getUserCourses();
        return view;
    }

    private void initializeViews() {
        activity.toolbar.setTitle(getString(R.string.registered_courses));
        adapter = new RemainingCoursesAdapter(getContext(), courses, true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void getUserCourses() {
        Call<Courses> remainingCourses = retrofit.create(Controller.class).getStudentRegisteredCourses(LoginActivity.username);
        Log.e("Completed Courses URL:", remainingCourses.request().url().toString());
        remainingCourses.enqueue(new Callback<Courses>() {
            @Override
            public void onResponse(Call<Courses> call, Response<Courses> response) {
                List<Courses.DataBean> dataBeans = response.body().getData();
                if (dataBeans != null && !dataBeans.isEmpty()) {
                    for (Courses.DataBean dataBean : dataBeans) {
                        // Adding description to the descriptions arraylist

                        courses.add(dataBean.getCourse());
                        //--------------------------------------------------------------------
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Courses> call, Throwable throwable) {

            }
        });
    }
}
