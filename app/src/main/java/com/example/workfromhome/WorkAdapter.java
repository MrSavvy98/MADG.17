package com.example.workfromhome;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ViewHolder> {
    private static final String TAG = WorkAdapter.class.getSimpleName();

    private LayoutInflater mInflater;
    private OnItemClickListener mItemClickListener;
    private List<Work> works = new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

    public WorkAdapter(final Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.one_work, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Work work = works.get(position);
        holder.date.setText(formatter.format(work.getDate()));
        holder.work.setText(work.getWork());
        holder.delay.setText(work.getDelay());
    }

    @Override
    public int getItemCount() {
        return works.size();
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, work, delay;
        ImageButton btnDelete, btnEdit;

        ViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.task);
            work = itemView.findViewById(R.id.work);
            delay = itemView.findViewById(R.id.delay);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(mItemClickListener != null && position >= 0) {
                        mItemClickListener.onItemEdited(position);
                    }
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(mItemClickListener != null && position >= 0) {
                        mItemClickListener.onItemDeleted(position);
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener ItemClickListener) {
        this.mItemClickListener = ItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemDeleted(int position);
        void onItemEdited(int position);
    }
}
