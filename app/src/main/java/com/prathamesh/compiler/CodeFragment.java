package com.prathamesh.compiler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.widget.CodeEditor;


public class CodeFragment extends Fragment {

    private FloatingActionButton runFab;

    public static CodeFragment newInstance(String param1, String param2) {
        return new CodeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_code, container, false);
       CodeEditor codeEditor = view.getRootView().findViewById(R.id.code_editor);
       codeEditor.setEditorLanguage(new JavaLanguage());

       runFab = view.getRootView().findViewById(R.id.codeFragRunFab);
       runFab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Home home = (Home)getActivity();

                if(!codeEditor.getText().toString().equals("")) {
                    home.showInputDialog(codeEditor.getText().toString());
                }
                else {
                    Dialogs.showDialog(getContext(), R.layout.null_code_warning_dialog,R.id.IVNullCodeDialog,R.drawable.null_code_warning);
                }

           }
       });

       return view;
    }
}