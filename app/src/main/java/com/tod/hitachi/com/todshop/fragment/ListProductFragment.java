package com.tod.hitachi.com.todshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tod.hitachi.com.todshop.R;
import com.tod.hitachi.com.todshop.Utility.GetAllData;
import com.tod.hitachi.com.todshop.Utility.MasterConstant;
import com.tod.hitachi.com.todshop.Utility.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListProductFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        create Listview
        createListview();


    }

    private void createListview() {
        ListView listView = getView().findViewById(R.id.listViewProduct);
        MasterConstant masterConstant = new MasterConstant();

        try {
            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(masterConstant.getUrlGetAllFood());
            String jsonString = getAllData.get();
            Log.d("7JuneV1","JSON ==>" + jsonString);

            JSONArray jsonArray =new JSONArray(jsonString);
            String[] nameStrings = new  String[jsonArray.length()];
            String[] priceStrings = new  String[jsonArray.length()];
            String[] detailStrings = new  String[jsonArray.length()];
            String[] photo = new  String[jsonArray.length()];

            for (int i=0 ; i<jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("NameFood");
                priceStrings[i] = jsonObject.getString("Price");
                detailStrings[i] = jsonObject.getString("Detail");
                photo[i] = jsonObject.getString("ImagePath");

                Log.d("7JuneV1","Path = "+ photo[i]);
            }

            ProductAdapter productAdapter = new ProductAdapter(getActivity(),nameStrings,priceStrings,detailStrings,photo);
            listView.setAdapter(productAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_product,container,false);
        return view;
    }
}
