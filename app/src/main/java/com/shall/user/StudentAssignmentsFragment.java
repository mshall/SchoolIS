package com.shall.user;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaurav.cdsrecyclerview.CdsRecyclerView;
import com.google.gson.Gson;
import com.shall.LoginActivity;
import com.shall.MainActivity;
import com.shall.R;
import com.shall.adapters.AssignmentsAdapter;
import com.shall.network.App;
import com.shall.network.Controller;
import com.shall.pojo.StudentAssignment;

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
public class StudentAssignmentsFragment extends Fragment {

    public static final String TAG = StudentAssignmentsFragment.class.getName();
    @BindView(R.id.recyclerView)
    CdsRecyclerView recyclerView;
    View view;
    @Inject
    Retrofit retrofit;
    ArrayList<StudentAssignment.Assignment> assignments;
    AssignmentsAdapter adapter;
    MainActivity activity;

    public StudentAssignmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assignments = new ArrayList<>();
        activity = (MainActivity) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_assignments, container, false);
        ButterKnife.bind(this, view);
        ((App) activity.getApplication()).getNetComponent().inject(this);
        initializeViews();
        getStudentAssignments();
        return view;
    }

    private void initializeViews() {
        activity.toolbar.setTitle(getString(R.string.student_assignments));
        adapter = new AssignmentsAdapter(getContext(), assignments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    public void getStudentAssignments() {

        Call<StudentAssignment> assignments = retrofit.create(Controller.class).getStudentAssignments(LoginActivity.username);
        Log.e("Assignments URL:", assignments.request().url().toString());
        assignments.enqueue(new Callback<StudentAssignment>() {

            @Override
            public void onResponse(Call<StudentAssignment> call, Response<StudentAssignment> response) {
                Gson gson = new Gson ();
                StudentAssignment studentAssignment = response.body();
                Log.e("PRINTING ASSIGNMENTS: ",gson.toJson(studentAssignment));
                List<StudentAssignment.Assignment> data = response.body().getData();
                for (StudentAssignment.Assignment bean : data) {
                    StudentAssignmentsFragment.this.assignments.add(bean);
                    adapter = new AssignmentsAdapter(getContext(), StudentAssignmentsFragment.this.assignments);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<StudentAssignment> call, Throwable throwable) {
                throwable.printStackTrace();
                throwable.getMessage();
            }
        });
    }
}
