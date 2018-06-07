package com.tod.hitachi.com.todshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tod.hitachi.com.todshop.R;
import com.tod.hitachi.com.todshop.ServiceActivity;
import com.tod.hitachi.com.todshop.Utility.GetAllData;
import com.tod.hitachi.com.todshop.Utility.MasterAlert;
import com.tod.hitachi.com.todshop.Utility.MasterConstant;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Register Controller
        registerController();
        // Login Controller
        loginController();


    }   // Main method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passString = passEditText.getText().toString().trim();

                MasterAlert masterAlert = new MasterAlert(getActivity());
                if (userString.isEmpty() || passString.isEmpty()) {
                    // Have space
                    masterAlert.normalDialog(getString(R.string.title_have_space),getString(R.string.message_have_space));
                } else {
                    // No Space
                    try {
                        MasterConstant masterConstant = new MasterConstant();

                        GetAllData getAllData = new GetAllData(getActivity());
                        getAllData.execute(masterConstant.getUrlGetAllUser());
                        String resultJSON = getAllData.get();
                        Log.d("6JuneV1","JSON ==> "+ resultJSON);

                        JSONArray jsonArray = new JSONArray(resultJSON);

                        Boolean b = true; //True == User false
                        String nameString = null;
                        String truePasswordString = null;
                        for (int i=0;i<jsonArray.length();i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (userString.equals(jsonObject.getString("User"))) {
                                b = false;
                                nameString = jsonObject.getString("Name");
                                truePasswordString = jsonObject.getString("Password");


                            }
                        } // for

                        // Check User
                        if (b) {
                            //User false
                            masterAlert.normalDialog("User False", "No " + userString + " in my database");
                        } else if (passString.equals(truePasswordString)) {
                            // Pass true
                            Log.d("6JuneV1" ,"Pass true");
                            Toast.makeText(getActivity(),"Welcome" + nameString,Toast.LENGTH_SHORT).show();

//                            Intent to Service Activity
                            Intent intent = new Intent(getActivity(), ServiceActivity.class);
                            intent.putExtra("NameUser",nameString);
                            startActivity(intent);

                            getActivity().finish(); // ปิดหน้าเดิม
                        } else {
                            // Password False
                            Log.d("6JuneV1","Pass false");
                            masterAlert.normalDialog("Password False","Please try again");
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("6JuneV1", e.toString());
                    }


                }



            }
        });
    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace fragment
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null) //หมายถึงคงหน้ากากเดิมไว้
                        .commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        return view;
    }
}
