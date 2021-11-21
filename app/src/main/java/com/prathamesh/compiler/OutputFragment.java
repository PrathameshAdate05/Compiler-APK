package com.prathamesh.compiler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OutputFragment extends Fragment {

    TextView output;

    public static OutputFragment newInstance(String param1, String param2) {
        return new OutputFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_output, container, false);

        output = view.getRootView().findViewById(R.id.TVOutputFrag);
        output.setHorizontallyScrolling(true);
        output.setMovementMethod(new ScrollingMovementMethod());
        

        return view;
    }
}