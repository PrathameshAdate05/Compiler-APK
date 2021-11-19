package com.prathamesh.compiler;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.prathamesh.compiler.ui.main.SectionsPagerAdapter;
import com.prathamesh.compiler.databinding.ActivityHomeBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Home extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityHomeBinding binding;

    private Spinner spinner;
    private Context c = this;
    private String languageKey;


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
        languageKey = Constants.LANGUAGES[position];
    }
    // spinner onNothingSelected method
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    // taking input from user
    public void showInputDialog(String code){
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View view = layoutInflater.inflate(R.layout.inputdialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(c);
        alertDialog.setView(view);

        final EditText userInput = (EditText) view.findViewById(R.id.ETInputDialog);
        alertDialog.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String input = userInput.getText().toString();
                        compile(code,getLanCode(languageKey),input);
                    }
                });
        AlertDialog alertDialogShow = alertDialog.create();
        alertDialogShow.show();
    }


    // compile methode to call api request and receive output
    private void compile(String code, String lancode, String input){

        // showing custom progressDialog
        CustomProgressDialog customProgressDialog = new CustomProgressDialog();
        customProgressDialog.showCustomProgressDialog(c);

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("code",code);
            jsonObject.put("language",lancode);
            jsonObject.put("input",input);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constants.URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    // dismiss custom progressDialog
                    customProgressDialog.dismissCustomProgressDialog();
                try {
                    String output = response.getString("output");
                    // setting output
                    setTextToOutputFragment(output);
                    Toast.makeText(Home.this, "Output Generated...", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // dismiss custom progressDialog
                customProgressDialog.dismissCustomProgressDialog();
                Dialogs.showDialog(c,R.layout.network_error_warning_dialog,R.id.IVNetworkError,R.drawable.no_network);
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);
    }


    // methode to set output in the OutputFragment's textview
    private void setTextToOutputFragment(String output){
        OutputFragment outputFragment = new OutputFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.outputFrag,outputFragment).commit();
        TextView TVOutput = findViewById(R.id.TVOutputFrag);
        TVOutput.setText(output);
    }

    // language codes hashmap
    private String getLanCode(String lanKey){
        HashMap<String, String> lanCodes = new HashMap<>();
        lanCodes.put("C","c");
        lanCodes.put("C++","cpp");
        lanCodes.put("Java","java");
        lanCodes.put("Python","py");
        lanCodes.put("Kotlin","kt");

        return  lanCodes.get(lanKey);
    }
}