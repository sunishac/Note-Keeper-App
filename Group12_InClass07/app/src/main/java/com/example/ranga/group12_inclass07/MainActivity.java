package com.example.ranga.group12_inclass07;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DataBaseDataManager dm;
    Button add;
    EditText et;
    Spinner sp;
    ListView lv;
    CheckBox cb;

    private String[] spin;
    private ArrayList<Notes> allNotes;
    private NoteAdapter noteAdapter;
    private String selectedDropDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DataBaseDataManager(this);
        et = (EditText) findViewById(R.id.inputID);
        sp = (Spinner) findViewById(R.id.priorityID);
        lv = (ListView) findViewById(R.id.lv1ID);
        cb = (CheckBox) findViewById(R.id.checkBox);


        this.spin = new String[]{"Priority","High","Medium","Low"};
        Spinner s = (Spinner)findViewById(R.id.priorityID);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spin);
        s.setAdapter(adapter);
        allNotes = new ArrayList<Notes>();

        add = (Button) findViewById(R.id.AddID);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1= et.getText().toString();
                String s2 = sp.getSelectedItem().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String s = sdf.format(new Date());
                String time = "time";
                String checkBox ="0";
               /* if(cb!=null){
                    checkBox ="0";
                }
                else{
                    checkBox ="1";
                }*/


                dm.saveNote(new Notes(s1,s2,checkBox,time));
                allNotes = dm.getNotes();
                Log.d("demo",allNotes.size()+"");
                noteAdapter = new NoteAdapter(MainActivity.this,R.layout.notes_lv,allNotes);
                noteAdapter.setNotifyOnChange(true);
                lv.setAdapter(noteAdapter);

            }
        });






        allNotes = dm.getNotes();
        Log.d("demo",allNotes.size()+"");
        lv.setClickable(true);
        lv.setLongClickable(true);

        noteAdapter = new NoteAdapter(MainActivity.this,R.layout.notes_lv,allNotes);
        noteAdapter.setNotifyOnChange(true);
        lv.setAdapter(noteAdapter);

        /*CheckBox cb = (CheckBox) findViewById(R.id.checkBox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                          @Override
                                          public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                              ArrayList < Notes > newAR = new ArrayList<Notes>();
                                              for(int j=0;j<allNotes.size();j++)
                                              {
                                                  if(!(allNotes.get(j).isStatusNew()))
                                                  {
                                                      newAR.add(allNotes.get(j));
                                                  }
                                              }
                                              for(int j=0;j<allNotes.size();j++)
                                              {
                                                  if(allNotes.get(j).get_id()!=newAR.get(j).get_id())
                                                  {
                                                      newAR.add(allNotes.get(j));

                                                  }

                                              }
                                              noteAdapter = new NoteAdapter(MainActivity.this,R.layout.notes_lv,newAR);
                                          }
                                      });*/


      /*  Collections.sort(allNotes, new Comparator<Notes>() {
            @Override
            public int compare(Notes itune1, Notes itune2)
            {
                return  itune1.setStatusNew().compareTo(itune2.price);
            }
        });*/


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override public boolean onItemLongClick(AdapterView<?> av, View v, final int pos, final long id) {
                final AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Do you really want to delete the task?");
                b.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                    } });
                b.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dm.delete(allNotes.get(pos));
                        allNotes = dm.getNotes();
                        noteAdapter = new NoteAdapter(MainActivity.this,R.layout.notes_lv,allNotes);
                        noteAdapter.setNotifyOnChange(true);
                        lv.setAdapter(noteAdapter);
                         } });
                b.show();  return true; } });






    }





    @Override
    protected void onDestroy() {
        dm.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.show_all:
                allNotes = dm.getNotes();
                noteAdapter = new NoteAdapter(MainActivity.this,R.layout.notes_lv,allNotes);
                noteAdapter.setNotifyOnChange(true);
                lv.setAdapter(noteAdapter);
                 break;

            case R.id.showcompleted:

                break;
            case R.id.showpending:

                break;
            case R.id.sort_time_id:

                break;

            case R.id.sort_priority_id:
              /*  Collections.sort(ins, new Comparator<iTunes>() {
                    @Override
                    public int compare(iTunes itune1, iTunes itune2)
                    {
                        return  itune2.price.compareTo(itune1.price);
                    }
                });
                adapter=new iTunesAdapter(MainActivity.this,R.layout.itunes_list_view,ins);
                lv.setAdapter(adapter);*/
                break;

        }

        return true;
    }


}
