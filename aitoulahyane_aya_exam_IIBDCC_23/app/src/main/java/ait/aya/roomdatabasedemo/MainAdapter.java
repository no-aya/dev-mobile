package ait.aya.roomdatabasedemo;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    // Initialize variable
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    //Create constructor
    public MainAdapter(Activity context, List<MainData> dataList)
    {
       this.context=context;
       this.dataList=dataList;
       notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Initialize view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ViewHolder holder, int position) {

        // Initialize main data
        final MainData data=dataList.get(position);

        // Initialize database
        database=RoomDB.getInstance(context);

        // Set text on text view
        holder.textView.setText(data.getText());
        holder.textView2.setText(data.getCapital());
        holder.textView3.setText(data.getHabitants().toString());


        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initialize main data
                MainData d=dataList.get(holder.getAdapterPosition());

                // Get id
                final int sID=d.getID();

                // Get text
                String sText=d.getText();
                String cText=d.getCapital();
                Float nText=d.getHabitants();

                // create dialog
                final Dialog dialog=new Dialog(context);


                // set content view
                dialog.setContentView(R.layout.dialog_update);

                //Initialize width
                int width= WindowManager.LayoutParams.MATCH_PARENT;

                //Initialize height
                int height=WindowManager.LayoutParams.WRAP_CONTENT;

                //Set layout
                dialog.getWindow().setLayout(width,height);

                //show dialog
                dialog.show();

                //Initialize and assign variable
                final EditText editText=dialog.findViewById(R.id.edit_text);
                final EditText editText2=dialog.findViewById(R.id.edit_text4);
                final EditText editText3 =dialog.findViewById(R.id.edit_text5);
                Button btUpdate=dialog.findViewById(R.id.bt_update);

                // Set text on edit text
                editText.setText(sText);
                editText2.setText(cText);
                editText3.setText(nText.toString());

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss Dialog
                        dialog.dismiss();

                        //Get Updated text from edit text
                        String uText=editText.getText().toString().trim();
                        String uCText=editText2.getText().toString().trim();
                        Float uNText=Float.parseFloat(editText3.getText().toString().trim());

                        // Update text in database
                        database.mainDao().update(sID, uText,uCText,uNText);

                        //notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();

                    }
                });

            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize main data
                MainData d=dataList.get(holder.getAdapterPosition());

                // Delete text from database
                database.mainDao().delete(d);

                // Notify when data is deleted
                int position=holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Initialize variable
        TextView textView, textView2, textView3;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assign variable

            textView=itemView.findViewById(R.id.text_view);
            textView2=itemView.findViewById(R.id.text_view_cap);
            textView3=itemView.findViewById(R.id.text_view_hab);


            btEdit=itemView.findViewById(R.id.bt_edit);
            btDelete=itemView.findViewById(R.id.bt_delete);
        }
    }
}
