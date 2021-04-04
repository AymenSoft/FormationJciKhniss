package com.app.formationjcikhniss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.formationjcikhniss.models.Users;
import com.app.formationjcikhniss.sqlite.UsersDB;

/**
 * update sqlite user
 * @author Aymen Masmoudi[04.04.2021]
 * */
public class UpdateUserActivity extends AppCompatActivity {

    private EditText etName, etEmail;
    private Button btnUpdate, btnDelete;

    private int userId;
    private String userName, userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        etName = findViewById(R.id.et_user_name);
        etEmail = findViewById(R.id.et_user_email);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        //click btnUpdate to save changes
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1-create new user object
                Users user = new Users();
                user.setId(userId);
                user.setName(etName.getText().toString());
                user.setEmail(etEmail.getText().toString());
                //2-save updates
                UsersDB db = new UsersDB(UpdateUserActivity.this);
                db.open();
                long update = db.update(user);
                db.close();
                if (update != 0) {
                    Toast.makeText(UpdateUserActivity.this, "success update", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });

        //delete user from database
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder delete = new AlertDialog.Builder(UpdateUserActivity.this);
                delete.setTitle("Delete");
                delete.setMessage("are you sure?");
                delete.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete.setCancelable(true);
                    }
                });
                delete.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UsersDB db = new UsersDB(UpdateUserActivity.this);
                        db.open();
                        long remove = db.remove(userId);
                        db.close();
                        if (remove != 0) {
                            Toast.makeText(UpdateUserActivity.this, "success delete", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                });
                delete.show();
            }
        });

        //get, read and show  data
        Intent data = getIntent();
        if (data != null) {
            //get, read data
            userId = data.getIntExtra("userId", 0);
            userName = data.getStringExtra("userName");
            userEmail = data.getStringExtra("userEmail");
            //show data
            etName.setText(userName);
            etEmail.setText(userEmail);
        }

    }
}