package com.prathamesh.compiler;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.prathamesh.compiler.ui.main.SectionsPagerAdapter;
import com.prathamesh.compiler.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityHomeBinding binding;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        spinner = findViewById(R.id.lanSpinner);
        // setting custom adapter to spinner
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(getApplicationContext(),Constants.ICONS,Constants.LANGUAGES);
        spinner.setAdapter(customSpinnerAdapter);
        spinner.setOnItemSelectedListener(this);

    }

    // spinner onItemSelected method
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(Home.this, Constants.LANGUAGES[position], Toast.LENGTH_SHORT).show();
    }
    // spinner onNothingSelected method
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}