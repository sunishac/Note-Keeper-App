package com.example.ranga.group12_inclass07;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.ranga.group12_inclass07.R.id.checkBox;

/**
 * Created by ranga on 2/27/2017.
 */

public class NoteAdapter extends ArrayAdapter<Notes> {
    DataBaseDataManager dm;
    Context mcontext;
    ArrayList<Notes> mdata;
    int mResouce;
    boolean bn;
    AlertDialog.Builder builder2;


    public NoteAdapter(Context context, int resource, ArrayList<Notes> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResouce=resource;
        this.mdata=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResouce,parent,false);
        }
        convertView.setEnabled(true);
        final TextView noteText = (TextView) convertView.findViewById(R.id.noteTextID);
        final TextView priority = (TextView) convertView.findViewById(R.id.priorID);
        final TextView time = (TextView) convertView.findViewById(R.id.timeID);
        final CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);
        cb.setEnabled(true);

        builder2=new AlertDialog.Builder(mcontext);
        builder2.setTitle("Do you really want to mark it as Pending??").setCancelable(false).
                setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // cb.setEnabled(true);
                cb.setChecked(true);

            }
        }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cb.setChecked(false);
                mdata.get(position).setStatusNew(bn);
            }
        });
        final AlertDialog simpleAlert2=builder2.create();
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bn =  b;
               /* if(b==true)
                {
                    cb.setChecked(false);
                    mdata.get(position).setStatusNew(b);
                }*/
                if(b==false)
                {
                    simpleAlert2.show();

                }
            }
        });
        final Notes note = mdata.get(position);
        noteText.setText(note.getNoteText());
        priority.setText(note.getPriority());
        time.setText(note.getUpdateTime());
        cb.setFocusable(false);
        convertView.setClickable(true);
        convertView.setLongClickable(true);

        //cb.setEnabled(bn);
        //convertView.setEnabled(true);
        return convertView;
    }
}
