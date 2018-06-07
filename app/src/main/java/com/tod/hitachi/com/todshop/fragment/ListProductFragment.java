package com.tod.hitachi.com.todshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
            final String[] nameStrings = new  String[jsonArray.length()];
            final String[] priceStrings = new  String[jsonArray.length()];
            final String[] detailStrings = new  String[jsonArray.length()];
            final String[] photo = new  String[jsonArray.length()];

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

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,
                                DetailFragment.detailInstance(nameStrings[position],
                                        priceStrings[position],
                                        detailStrings[position],
                                        photo[position]))
                        .addToBackStack(null)
                        .commit();



                }
            });


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
