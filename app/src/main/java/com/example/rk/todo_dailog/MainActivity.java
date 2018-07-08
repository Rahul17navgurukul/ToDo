package com.example.rk.todo_dailog;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    // Firebse
    private FirebaseAuth mAuth;
    Button mLogOut;

    FloatingActionButton fab;
    ImageView opentst;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    private DrawerLayout mDrawerLayout;

    ////******RECYCLE VIEW *******
//    RecyclerView listitem;
//    RecyclerView.LayoutManager layoutManager;


    String Text,text3;
    ArrayList<String> something=new ArrayList<>();


    //ArrayList<String> description=new ArrayList<>();


//    private RecyclerView recyclerView;
//    private Recycler_adapter adapter;
//    private ArrayList<String> listItems;
//
//    ArrayList<String> msg=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Firbase
        mAuth = FirebaseAuth.getInstance();

        ///*****RECYCLE VIEW*****


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        recyclerView.setAdapter(new Recycler_adapter(something));
//
//        adapter = new Recycler_adapter(listItems, this);
//        recyclerView.setAdapter(adapter);
//
//        listItems.add(new RecyclerView(msg));


        //recyclerView.setAdapter(new Recycler_adapter(description));




         // your listview's id here
//        .setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                listView.removeViewAt(position);
//            }
//        });




        mDrawerLayout = findViewById(R.id.drawer_layout);

//        toolbar =  findViewById(R.id.toolbar);
//        setSupportActionBar();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                Toast.makeText(MainActivity.this,"you clicked",Toast.LENGTH_LONG).show();
                                return false;
                            }
                        });

                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


        opentst = findViewById(R.id.opentask);


        // when add press Dialog box apear
        opentst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dailog_box, null);
                final EditText mtext = (EditText) mView.findViewById(R.id.etText);
                //final EditText mdes = (EditText) mView.findViewById(R.id.add_discription);

                Button msave = (Button) mView.findViewById(R.id.btnsave);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                // here i get text frm aert pop window
                msave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {




                        Text = mtext.getText().toString();
                        something.add(Text);
                        myRef.setValue(Text);
                        mtext.setText("");
//
//                        Intent i = new Intent(MainActivity.this,Recycler_adapter.class);
//                        i.putExtra("msg",mdes.getText().toString());
//                        startActivity(i);


//                        TextView des = (TextView)findViewById(R.id.description);
//
//                       String msg = mdes.getText().toString();
//                       des.setText(msg);




                        dialog.dismiss();
                    }

                });


            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            // i make sendtostart methord for Ui update
            sendToStart();
        }

    }

    // ui updating her
    private void sendToStart() {
        //when logout btn press back to start page
        Intent Start = new Intent(MainActivity.this, Start_activity.class);
        startActivity(Start);

    }




    // logout btn her
//    @Override
//    public void onClick(View v) {
//        FirebaseAuth.getInstance().signOut();
//        sendToStart();
//    }


    // when i press back btn it show pop up and asking for close
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("No", null).show();


    }

    public void do_somethimg(MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        sendToStart();

    }

    public void do_nothing(MenuItem item) {

        Intent note = new Intent(MainActivity.this,Note.class);
        startActivity(note);

    }


}
