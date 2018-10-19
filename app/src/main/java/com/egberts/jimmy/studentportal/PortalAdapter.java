package com.egberts.jimmy.studentportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PortalAdapter extends RecyclerView.Adapter<PortalAdapter.PortalViewHolder> {

    final private PortalClickListener mPortalClickListener;
    List<Portal> mPortals;
    // Specify how many views adapter hold
    private int mNumberItems;

    private Context mContext;

    // Populate that var in the constructor
    public PortalAdapter(Context mContext, List<Portal> portals, PortalClickListener mPortalClickListener) {
        mPortals = portals;
        this.mContext = mContext;
        this.mPortalClickListener = mPortalClickListener;
    }

    /**
     * Sets the layout that needs to be used in the recyclerview
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public PortalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.portal_grid_cell, viewGroup, false);

        return new PortalViewHolder(view);
    }

    /**
     * Set the layout items in the grid_cell for each item in the recyclerview
     * @param portalViewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull PortalViewHolder portalViewHolder, int position) {
        final Portal portal = mPortals.get(position);
        portalViewHolder.mPortalName.setText(portal.getmTitle());
    }

    /**
     * Get all the items that need to be shown in the recyclerview
     * @return
     */
    @Override
    public int getItemCount() {
        return mPortals.size();
    }

    /**
     * Interface for making it possible to make a clicklistner for each viewholder
     */
    public interface PortalClickListener {
        void reminderOnClick(int i);
    }

    public class PortalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mPortalName;

        public View mView;

        public PortalViewHolder(@NonNull View itemView) {
            super(itemView);
            mPortalName = itemView.findViewById(R.id.portalTextView);
            mView = itemView;
            itemView.setOnClickListener(this);
        }

        //When an item in the RecyclerView is clicked the AdapterPosition get retrieved and on the clicked position reminderOnClick gets executed
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mPortalClickListener.reminderOnClick(clickedPosition);
        }
    }
}
