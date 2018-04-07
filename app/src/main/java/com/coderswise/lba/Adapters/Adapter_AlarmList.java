package com.coderswise.lba.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderswise.lba.R;
import com.coderswise.lba.model.ModelAlarmList;
import com.coderswise.lba.model.SQL_Helper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Jakir Hossain
 * Date: 2/27/2017.
 */
public class Adapter_AlarmList extends RecyclerView.Adapter<Adapter_AlarmList.ListViewHolder>{

    private ArrayList<ModelAlarmList> list;
    private Context context;
    private SQL_Helper sql_helper;

    public Adapter_AlarmList(ArrayList<ModelAlarmList> list, Context context) {
        this.list = list;
        this.context = context;
        sql_helper=new SQL_Helper(context);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_alarm_list,parent,false);
        ListViewHolder viewHolder=new ListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, final int position) {
        holder.tvAlarmTitle.setText(list.get(position).getLocationTitle());
        holder.getTvAlarmDescription.setText(list.get(position).getAlarmNotification());
        holder.switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int id = list.get(position).getAlarmId();
                if (isChecked){
//                    sql_helper.updateState(id,1);
//                    Intent startReminderService=new Intent(context, ReminderService.class);
//                    context.startService(startReminderService);
                }
                else {
//                    sql_helper.updateState(id,0);
//                    Intent startReminderService=new Intent(context, ReminderService.class);
//                    context.stopService(startReminderService);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.imvAlarmLogo)
        ImageView getImvAlarmLogo;
        @InjectView(R.id.tvAlarmTitle)
        TextView tvAlarmTitle;
        @InjectView(R.id.tvAlarmNotification)
        TextView getTvAlarmDescription;
        @InjectView(R.id.switchOnOff)
        SwitchCompat switchOnOff;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
