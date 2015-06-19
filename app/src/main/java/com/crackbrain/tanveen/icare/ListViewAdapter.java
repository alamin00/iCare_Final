package com.crackbrain.tanveen.icare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Crack Brain on 5/12/2015.
 */
public class ListViewAdapter extends BaseAdapter
{
    private ArrayList<User> userArrayList;
    private Context context;
    private LayoutInflater inflater;
    private DBAdapter dbAdapter;
    public ListViewAdapter(Context context, ArrayList<User> userArrayList)
    {
        this.context = context;
        this.userArrayList = userArrayList;
        inflater = LayoutInflater.from(context);
        dbAdapter = new DBAdapter(context);
    }

    @Override
    public int getCount() {
        return userArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.sample_listview, parent, false);
            TextView tvName = (TextView) view.findViewById(R.id.tvEmployeeName);
            tvName.setText(userArrayList.get(position).getName());
            tvName.setTag(userArrayList.get(position));
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = ((User) v.getTag()).getId();
                    Intent gotoEmployeeDetailsActivity = new Intent(context, UserDetailsActivity.class);
                    gotoEmployeeDetailsActivity.putExtra("id", id);
                    context.startActivity(gotoEmployeeDetailsActivity);

                }
            });
            tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int id = ((User) v.getTag()).getId();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setTitle("Confirm Delete...");
                    alertDialog.setMessage("Are you sure you want to delete this?");
                    alertDialog.setIcon(R.drawable.ic_delete);

                    alertDialog.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                    dbAdapter.delete(id);
                                    userArrayList = dbAdapter.getEmployeeInfo();
                                    notifyDataSetChanged();
                                }
                            });
                    alertDialog.setNeutralButton("All",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0,int arg1) {
                                    dbAdapter.allDelete();
                                    userArrayList = dbAdapter.getEmployeeInfo();
                                   notifyDataSetChanged();
                                }
                            });

                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "You clicked on NO", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });
                    alertDialog.show();
                    return false;
                }
            });
        } else {
            view = convertView;
        }

        return view;
    }
}
