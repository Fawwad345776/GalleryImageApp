package com.example.task;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    private static final int GalleryPick = 1;
    int[] data;
    String[] lang;
    String[] l;
    private CallbackInterface mCallback;
    private Context mContext;

    public interface CallbackInterface
    {
        void onHandleSelection(int position, ProgrammingViewHolder holder);
    }


    public ProgrammingAdapter(Context context,int[] data, String[] lang, String[] l)
    {
        this.data = data;
        this.lang = lang;
        this.l = l;
        mContext = context;

        try
          {
            mCallback = (CallbackInterface) context;
          }
        catch(ClassCastException ex)
          {
            //.. should log the error or throw and exception
            Log.e("MyAdapter","Must implement the CallbackInterface in the Activity", ex);
          }

    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProgrammingViewHolder holder, final int position) {
        final int title = data[position];
        holder.imageView.setImageResource(title);
        final String txt = lang[position];
        holder.textView.setText(txt);
        String txt2 = l[position];
        holder.textView2.setText(txt2);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(), "Click on " + txt, Toast.LENGTH_SHORT).show();
            }
        });


        holder.mChange.setOnClickListener(new View.OnClickListener()  {
            @Override

            public void onClick(View v)
            {
                if(mCallback != null){
                    mCallback.onHandleSelection(position, holder);
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class ProgrammingViewHolder extends RecyclerView.ViewHolder     {
        ImageView imageView;
        TextView textView;
        TextView textView2;
        View view;
        Button mChange;


        public ProgrammingViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgIcon);
            textView = (TextView) itemView.findViewById(R.id.title_text);
            textView2 = (TextView) itemView.findViewById(R.id.title_text2);
            view = itemView;
            mChange = (Button) itemView.findViewById(R.id.button2);

        }




    }


}

