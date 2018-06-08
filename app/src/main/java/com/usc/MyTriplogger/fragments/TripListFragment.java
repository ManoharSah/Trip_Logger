package com.usc.MyTriplogger.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.usc.MyTriplogger.controllers.TripActivity;
import com.usc.MyTriplogger.adapters.MyTripLogsAdapter;
import com.usc.MyTriplogger.lab.LogLab;
import com.usc.MyTriplogger.R;
import com.usc.MyTriplogger.controllers.SettingsActivity;
import com.usc.MyTriplogger.models.Trip;

import java.util.List;

public class TripListFragment extends Fragment {

    private MyTripLogsAdapter mAdapter;
    private RecyclerView mLogRecyclerView;

    private Button mLogButton;
    private Button mSettingsButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment_log_list,container,false);

        mLogButton = (Button) view.findViewById(R.id.btn_log);
        mSettingsButton = (Button) view.findViewById(R.id.btn_settings);
        mLogRecyclerView = (RecyclerView) view.findViewById(R.id.logList);

        mLogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Trip a = new Trip();
                LogLab.get(getActivity()).addActivity(a);
                Intent intent = TripActivity.newIntent(getActivity(), a.getId());
                startActivity(intent);

            }
        });

        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = SettingsActivity.newIntent(getActivity());
                startActivity(intent);

            }
        });

        updateUI();

        return view;

    }

    private void updateUI() {
        LogLab Loglab = LogLab.get(getActivity());
        List<Trip> logs = Loglab.getActivities();

        if(mAdapter == null){
            mAdapter = new MyTripLogsAdapter(getActivity(),logs);
            mLogRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setLogs(logs);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

}
