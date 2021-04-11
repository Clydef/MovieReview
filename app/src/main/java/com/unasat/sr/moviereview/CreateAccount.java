package com.unasat.sr.moviereview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    EditText firstNameEdtTxt, lastNameEdtText, emailEdtTxt, passwordEdtTxt, passwordRetpeEdtText, countryEdtTxt;
    Button createAccountBtn, viewDataBtn, deleteDataBtn, updateDataBtn;
    MovieReviewDatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firstNameEdtTxt = findViewById(R.id.firstNameEdtTxt);
        lastNameEdtText = findViewById(R.id.lastNameEdtText);
        emailEdtTxt = findViewById(R.id.emailEdtTxt);
        passwordEdtTxt = findViewById(R.id.passwordEdtTxt);
        passwordRetpeEdtText = findViewById(R.id.passwordRetpeEdtText);
        countryEdtTxt = findViewById(R.id.countryEdtTxt);

        createAccountBtn = findViewById(R.id.createAccountBtn);
        viewDataBtn = findViewById(R.id.viewDataBtn);
        deleteDataBtn = findViewById(R.id.deleteDataBtn);
        updateDataBtn = findViewById(R.id.updateBtn);

        // This will get the radiogroup
        RadioGroup rGroup = (RadioGroup) findViewById(R.id.radioGroup);
        // This will get the radiobutton in the radiogroup that is checked
        //RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(rGroup.getCheckedRadioButtonId());
        final RadioButton[] checkedRadioButton = {null};

        DB = new MovieReviewDatabaseHelper(this);

        // This overrides the radiogroup onCheckListener
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // This will get the radiobutton that has changed in its check state
                 checkedRadioButton[0] = (RadioButton) group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton[0].isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    Toast.makeText(CreateAccount.this, "Checked:" + checkedRadioButton[0].getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEdtTxt.getText().toString();
                String lastName = lastNameEdtText.getText().toString();
                String email = emailEdtTxt.getText().toString();

                String gender = checkedRadioButton[0].getText().toString();

                //String passwordRetpe = passwordRetpeEdtText.getText().toString();
                String country = countryEdtTxt.getText().toString();

                Boolean checkInsertData = DB.insertNewMember(firstName, lastName, gender, email,/*password, passwordRetpe,*/ country);
                if (checkInsertData) {
                    Toast.makeText(CreateAccount.this, "Account created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateAccount.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = firstNameEdtTxt.getText().toString();

                Boolean checkDeleteData = DB.deleteMember(firstname);
                if (checkDeleteData) {
                    Toast.makeText(CreateAccount.this, "Account deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateAccount.this, "Could not delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = firstNameEdtTxt.getText().toString();
                String email = emailEdtTxt.getText().toString();

                Boolean checkUpdateData = DB.updateMember(firstname, email);
                if (checkUpdateData) {
                    Toast.makeText(CreateAccount.this, "Emailadres updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateAccount.this, "Could not update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = DB.getData();
                if (result.getCount() == 0) {
                    Toast.makeText(CreateAccount.this, "No data", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()) {
                    buffer.append("First Name: " + result.getString(1) + "\n");
                    buffer.append("Last Name: " + result.getString(2) + "\n");
                    buffer.append("Gender: " + result.getString(3) + "\n");
                    buffer.append("Email: " + result.getString(4) + "\n");
                    buffer.append("Country: " + result.getString(5) + "\n");
                    buffer.append("Register Date: " + result.getString(6) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccount.this);
                builder.setCancelable(true);
                builder.setTitle("Members");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}