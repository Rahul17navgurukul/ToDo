package com.example.rk.todo_dailog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Recycler_adapter extends RecyclerView.Adapter<Recycler_adapter.ViewHolder>{

//    private String[] data;
    private ArrayList<String> data;
    //private ArrayList<String> listItems;


    private Context context;








    public Recycler_adapter(ArrayList<String> data){
        this.data = data;

       this.context = context;
//        inflater = LayoutInflater.from(context);




    }





    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,  final int position) {

        holder.textView.setText(data.get(position));
        //holder.textView2.setText(data.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Display option menu

                PopupMenu popupMenu = new PopupMenu(context, holder.textView);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.mnu_item_save:
                                Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.mnu_item_delete:
                                //Delete item
                                data.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView,textView2;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.tv_add);
            //textView2 = (TextView)itemView.findViewById(R.id.description);




        }


    }
}
