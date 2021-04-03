package com.app.formationjcikhniss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.app.formationjcikhniss.adapters.UsersAdapter;
import com.app.formationjcikhniss.models.Users;
import com.app.formationjcikhniss.sqlite.UsersDB;

import java.util.ArrayList;

/**
 * application home activity
 * @author Aymen Masmoudi[03.04.2021]
 * */
public class HomeScreenActivity extends AppCompatActivity {

    private EditText etName, etEmail;
    private Button btnDisconnect, btnSave;
    private ListView lvUsers;

    private SharedPreferences userPrefs;

    private UsersAdapter adapter;
    private ArrayList<Users> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        userPrefs = getSharedPreferences("userPreferences", MODE_PRIVATE);

        etName = findViewById(R.id.et_user_name);
        etEmail = findViewById(R.id.et_user_email);
        btnDisconnect = findViewById(R.id.btn_disconnect);
        btnSave = findViewById(R.id.btn_save);
        lvUsers = findViewById(R.id.lv_users);

        //save a new user
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty()) {
                    Toast.makeText(HomeScreenActivity.this, "please complete all fields", Toast.LENGTH_SHORT).show();
                }else {
                    //1-create new user and set data
                    Users user = new Users();
                    user.setName(etName.getText().toString());
                    user.setEmail(etEmail.getText().toString());
                    //2-open data base and save user
                    UsersDB db = new UsersDB(HomeScreenActivity.this);
                    db.open();
                    long insert = db.insert(user);
                    if (insert > 0) {
                        arrayList = db.getAllUsers();
                        if (!arrayList.isEmpty()) {
                            adapter = new UsersAdapter(HomeScreenActivity.this, R.layout.item_users, arrayList);
                            lvUsers.setAdapter(adapter);
                        }
                    }
                    db.close();
                }
            }
        });

        //show user from list
        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = arrayList.get(position).getName();
                Toast.makeText(HomeScreenActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });

        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder exit = new AlertDialog.Builder(HomeScreenActivity.this);
                exit.setTitle("Déconnection");
                exit.setMessage("Voulez-vous vraiment se déconnecter?");
                exit.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exit.setCancelable(true);
                    }
                });
                exit.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //1-clean shared preferences
                        SharedPreferences.Editor editor = userPrefs.edit();
                        editor.clear();
                        editor.apply();
                        //2-open login activity
                        Intent login = new Intent(HomeScreenActivity.this, LoginActivity.class);
                        startActivity(login);
                        //3-close home screen activity
                        finish();
                    }
                });
                exit.show();
            }
        });

        //read all users
        getUsers();

    }

    //read all users
    private void getUsers(){
        UsersDB db = new UsersDB(HomeScreenActivity.this);
        db.open();
        arrayList = db.getAllUsers();
        if (!arrayList.isEmpty()) {
            adapter = new UsersAdapter(HomeScreenActivity.this, R.layout.item_users, arrayList);
            lvUsers.setAdapter(adapter);
        }
        db.close();
    }

}