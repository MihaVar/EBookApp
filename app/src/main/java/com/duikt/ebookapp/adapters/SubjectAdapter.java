package com.duikt.ebookapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duikt.ebookapp.PdfReaderAcitivity;
import com.duikt.ebookapp.R;
import com.duikt.ebookapp.models.SubjectModel;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.viewHolder> {

    Context context;
    ArrayList<SubjectModel> list;

    public SubjectAdapter(Context context, ArrayList<SubjectModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        SubjectModel model = list.get(position);

        holder.subjectName.setText(model.getSubjectName());
        holder.subjectImage.setImageResource(model.getSubjectImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PdfReaderAcitivity.class);
                intent.putExtra("name", model.getSubjectName());
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView subjectName;
        ImageFilterView subjectImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.chapterName);
            subjectImage = itemView.findViewById(R.id.subjectImageView);
        }
    }
}
