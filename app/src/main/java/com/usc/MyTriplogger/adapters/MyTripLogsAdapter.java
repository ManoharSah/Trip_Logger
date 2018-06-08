package com.usc.MyTriplogger.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.usc.MyTriplogger.R;
import com.usc.MyTriplogger.controllers.TripActivity;
import com.usc.MyTriplogger.fragments.TripViewFragment;
import com.usc.MyTriplogger.models.Trip;

import java.util.List;

public class MyTripLogsAdapter extends RecyclerView.Adapter<MyTripLogsAdapter.LogHolder> {

    private Context mContext;
    private List<Trip> mTrips;

    public MyTripLogsAdapter(Context context, List<Trip> logs){
        this.mContext = context;
        this.mTrips = logs;
    }

    @Override
    public LogHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.my_log_item,parent,false);
        return new LogHolder(view);

    }

    @Override
    public void onBindViewHolder(LogHolder holder, int position) {

        final Trip myLog = mTrips.get(position);

        holder.bindLog(myLog);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                TripViewFragment myFragment = new TripViewFragment();

                Bundle bundle = new Bundle();
                bundle.putString(TripActivity.EXTRA_LOG_ID, myLog.getId().toString());
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });



    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }

    public class LogHolder extends RecyclerView.ViewHolder  {

        private Trip mLog;

        private TextView txtTitle;
        private TextView txtDate;
        private TextView txtDesc;

        public LogHolder(final View itemView){
            super(itemView);
            txtDate = (TextView) itemView.findViewById(R.id.date);
            txtDesc = (TextView) itemView.findViewById(R.id.desc);
            txtTitle = (TextView) itemView.findViewById(R.id.title);
        }

        public void bindLog(Trip trip){
            mLog = trip;
            txtDate.setText(mLog.getDate());
            txtTitle.setText(mLog.getTitle());
            txtDesc.setText(mLog.getDestination());
        }

    }

    public void setLogs(List<Trip> trips){
        mTrips = trips;
    }

}
