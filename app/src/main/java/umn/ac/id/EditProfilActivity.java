package umn.ac.id;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class EditProfilActivity extends AppCompatActivity {
    private TextView btArrow, btSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofil_screen);

        UserFragment fragment = new UserFragment();
        FragmentManager manager = getSupportFragmentManager();

    btArrow = (TextView) findViewById(R.id.backarrow);
    btSave = (TextView) findViewById(R.id.save);

        }

    public void goToAttract(View view) {
        Intent intent = new Intent(this, UserFragment.class);
        startActivity(intent);
    }


    }

