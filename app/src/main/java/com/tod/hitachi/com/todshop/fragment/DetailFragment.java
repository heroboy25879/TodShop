package com.tod.hitachi.com.todshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tod.hitachi.com.todshop.R;


public class DetailFragment extends Fragment{

    private String nameString , priceString , detailString , photoString;

    public static DetailFragment detailInstance(String nameString,
                                                String priceString,
                                                String detailString,
                                                String photo) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Name",nameString);
        bundle.putString("Price",priceString);
        bundle.putString("Detail",detailString);
        bundle.putString("Photo",photo);

        detailFragment.setArguments(bundle);

        return detailFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // get value from argument
        getValueFromArugument();

        //back controller
        Button button = getView().findViewById(R.id.btnBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .popBackStack();
            }
        });
    }

    private void getValueFromArugument() {
        nameString = getArguments().getString("Name");
        priceString = getArguments().getString("Price");
        detailString = getArguments().getString("Detail");
        photoString = getArguments().getString("Photo");
        Log.d("7JuneV1","Photo ="+photoString);


        //Show view
        ImageView imageView = getView().findViewById(R.id.imgShowPic);
        Picasso.get().load(photoString).into(imageView);

        TextView nametextView = getView().findViewById(R.id.txtName);
        nametextView.setText(nameString);

        TextView princeTextView = getView().findViewById(R.id.txtPrice);
        princeTextView.setText(priceString);

        TextView detailTextView = getView().findViewById(R.id.txtDetail);
        detailTextView.setText(detailString);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail,container,false);

        return view;
    }
}
