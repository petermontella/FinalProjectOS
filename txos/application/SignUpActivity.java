package com.example.fransel.gopartyapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class SignUpActivity extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String d ="", m ="";
            if(dayOfMonth < 10 )
                d = "0"+dayOfMonth;
            else
                d = ""+dayOfMonth;
            if(month+1 < 10)
                m = "0"+(month+1);
            else
                m = ""+(month+1);

            dateOfBirthField.setText(m+"/"+d+"/"+year);
        }
    };

    DatabaseHelper myDb;
    TextView nameText, lastNameText, genderText, dateOfBirthText, schoolText, emailText, passwordText, passwordConfirmText,title;
    EditText nameField, lastNameField, dateOfBirthField, schoolField, emailField, passwordField, passwordConfirmField;
    Button createAccountButton, backButton, date;
    RadioButton maleRadioButton, femaleRadioButton;
    boolean isDublicate = false;
    private ActionMenuView leftMenu;
    PasswordSecurity passwordSec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        myDb = new DatabaseHelper(this);
        passwordSec = new PasswordSecurity();

        title = (TextView)findViewById(R.id.toolbarTitle);
        title.setText("Sign Up");




        Toolbar t = (Toolbar) findViewById(R.id.toolbar);
        leftMenu = (ActionMenuView) t.findViewById(R.id.leftMenu);


        leftMenu.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return onOptionsItemSelected(menuItem);
            }
        });


        setSupportActionBar(t);

        // Connect Text Views to variables
        nameText = (TextView) findViewById(R.id.nameText);
        lastNameText = (TextView) findViewById(R.id.lastNameText);
        genderText = (TextView) findViewById(R.id.genderText);
        dateOfBirthText = (TextView) findViewById(R.id.dateOfBirthText);
        schoolText = (TextView) findViewById(R.id.schoolText);
        emailText = (TextView) findViewById(R.id.emailText);
        passwordText = (TextView) findViewById(R.id.passwordText);
        passwordConfirmText = (TextView) findViewById(R.id.passwordConfirmText);


        // Connect Text Field to variables
        nameField = (EditText) findViewById(R.id.nameField);
        lastNameField = (EditText) findViewById(R.id.lastNameField);
        //genderField = (EditText) findViewById(R.id.genderField);
        dateOfBirthField = (EditText) findViewById(R.id.dateOfBirthField);
        schoolField = (EditText) findViewById(R.id.schoolField);
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        passwordConfirmField = (EditText) findViewById(R.id.passwordConfirmField);


        // Connect a button to a variable
        createAccountButton = (Button) findViewById(R.id.createAccountButton);

        maleRadioButton = (RadioButton) findViewById(R.id.maleRadioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.femaleRadioButton);
        date = (Button)findViewById(R.id.date);



        AddData();
        //String email = emailText.getText().toString();
        //String password = passwordText.getText().toString();





        date.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            new DatePickerDialog(SignUpActivity.this,listener, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
        }

    });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        // Items on the left
        inflater.inflate(R.menu.menu_back,leftMenu.getMenu());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Do your actions here

        int resId = item.getItemId();

        if(resId == R.id.toolbarBack){
            Intent newPane = new Intent(SignUpActivity.this, LogInActivity.class);
            startActivity(newPane);
        }
        return true;
    }


    public boolean checkDublicate(){
        Cursor rs = myDb.getDataFromProfiles();


        if(rs.getCount() == 0) {

        }
        else {
            String email;
          //  StringBuffer buffer = new StringBuffer();
            email = emailField.getText().toString();
            while (rs.moveToNext()) {
                if (email.equals(rs.getString(6))) {
                    isDublicate = true;
                }
            }
        }
        return isDublicate;
    }



    public void AddData(){
// The following actions when the button is clicked
        createAccountButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view){

                checkDublicate();

                String name,lastName, gender = "", DOB, school, email, password, passwordConfirm;
                name = nameField.getText().toString();
                lastName = lastNameField.getText().toString();

                if(maleRadioButton.isChecked())
                gender = maleRadioButton.getText().toString();
                else if (femaleRadioButton.isChecked())
                gender = femaleRadioButton.getText().toString();


                DOB = dateOfBirthField.getText().toString();
                school = schoolField.getText().toString();
                email = emailField.getText().toString().trim();
                password = passwordField.getText().toString().trim();
                passwordConfirm = passwordConfirmField.getText().toString().trim();


                int checkMe =0;
                boolean empty =false;
                if(name.isEmpty())
                    checkMe++;
                if(lastName.isEmpty())
                    checkMe++;
                if(gender.isEmpty())
                    checkMe++;
                if(DOB.isEmpty())
                    checkMe++;
                if(school.isEmpty())
                    checkMe++;
                if(email.isEmpty())
                    checkMe++;
                if(password.isEmpty())
                    checkMe++;
                if(passwordConfirm.isEmpty())
                    checkMe++;

                if(checkMe > 0)
                   empty = true;

                // Pass into a database
                boolean isInserted =false;

                if(!isDublicate) {
                    if(empty ==true)
                        Toast.makeText(SignUpActivity.this, "One of the fields is missing information. Please fill out the blanks!", Toast.LENGTH_LONG).show();
                    else {
                        String encryptedPassword = "";
                        try {
                            encryptedPassword = passwordSec.encrypt(password).trim();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        isInserted = myDb.insertIntoProfiles(name, lastName, gender, DOB, school, email, encryptedPassword);
                        Intent newPane = new Intent(SignUpActivity.this, LogInActivity.class);
                        startActivity(newPane);
                    }
                }
                else
                    Toast.makeText(SignUpActivity.this, "You already have an account", Toast.LENGTH_LONG).show();

                if(isInserted)
                    Toast.makeText(SignUpActivity.this, "Account Successfully Created", Toast.LENGTH_LONG).show();






            }

        });
    }

}

