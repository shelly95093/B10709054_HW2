package com.example.b10709054_hw2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.b10709054_hw2.data.WaitlistContract;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {
    private int color;
    private Cursor mCursor;
    private Context mContext;
    private TextView nameTextView;
    private TextView partySizeTextView;
    private Resources r ;
    public SharedPreferences sharedPreferences;

    public GuestListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        r = context.getResources();
    }

    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.forecast_list_item, parent, false);
        setupColor();
        return new GuestViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position))
            return;
        String name = mCursor.getString(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME));
        int partySize = mCursor.getInt(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE));
        long id = mCursor.getLong(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry._ID));
        setupColor();
        nameTextView = holder.nameTextView;
        partySizeTextView = holder.partySizeTextView;
        nameTextView.setText(name);
        partySizeTextView.setText(String.valueOf(partySize));
        holder.partySizeTextView.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        holder.itemView.setTag(id);
    }

    public void setupColor(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String c =sharedPreferences.getString("pref_color_key","green");
        switch (c){
            case ("red"):{
                color= Color.RED;
                break;
            }
            case ("blue"):{
                color= Color.BLUE;
                break;
            }
            case ("green"):{
                color= Color.GREEN;
                break;
            }
        }
    }



    class GuestViewHolder extends RecyclerView.ViewHolder {


        TextView nameTextView;

        TextView partySizeTextView;


        public GuestViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.guest_number);
            partySizeTextView = (TextView) itemView.findViewById(R.id.guest_name);
        }

    }
}
