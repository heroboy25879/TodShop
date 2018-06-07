package com.tod.hitachi.com.todshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tod.hitachi.com.todshop.MainActivity;
import com.tod.hitachi.com.todshop.R;
import com.tod.hitachi.com.todshop.Utility.MasterAlert;
import com.tod.hitachi.com.todshop.Utility.MasterConstant;
import com.tod.hitachi.com.todshop.Utility.PostUsertoServer;

public class RegisterFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        createToolbar();


    }//Main method


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //เมื่อมีการคลิก
        if (item.getItemId() == R.id.itemsave) {
            // To Do
            SaveValueToServer();
            return true;

        }


        return super.onOptionsItemSelected(item);
    }

    private void SaveValueToServer() {

//        get value from edit text
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText UserEditText = getView().findViewById((R.id.edtUser));
        EditText PassEditText = getView().findViewById(R.id.edtPassword);

        //Change Data type to string
        String nameString = nameEditText.getText().toString().trim();
        String userString = UserEditText.getText().toString().trim();
        String passString = PassEditText.getText().toString().trim();


        // Check space
        if (nameString.isEmpty() || userString.isEmpty() || passString.isEmpty())
        {
            MasterAlert masterAlert = new MasterAlert(getActivity());
            masterAlert.normalDialog("Have Space",
                    "Please Fill Every Blank");
        }
        else
        {
            try {
                PostUsertoServer postUsertoServer = new PostUsertoServer(getActivity());
                MasterConstant masterConstant = new MasterConstant();

                postUsertoServer.execute(nameString,userString,passString,masterConstant.getUrlAddUser());


                String resultString = postUsertoServer.get();
                Log.d("6JuneV1","result ==>" + resultString);

                if (Boolean.parseBoolean(resultString)) {
                    getActivity().getSupportFragmentManager().popBackStack(); //กลับไปก่อนหน้า
                } else {
                    MasterAlert masterAlert = new MasterAlert((getActivity()));
                    masterAlert.normalDialog("Cannot Upload","Please try again Upload false");
                }


            }catch (Exception e) {
                e.printStackTrace();
            }



        }// if




    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_register, menu);

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);

        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

//        Setup title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Please Fill Every Blank");

        //Show navigator
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().popBackStack();//กลับไปหน้าที่แล้ว

            }
        });


        setHasOptionsMenu(true); //อนุญาตให้มีเมนูบน toolbar
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);


        return view;
    }
}
