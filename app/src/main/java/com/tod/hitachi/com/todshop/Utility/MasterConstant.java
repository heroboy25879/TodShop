package com.tod.hitachi.com.todshop.Utility;

import com.tod.hitachi.com.todshop.R;

public class MasterConstant {

    //About Array
    private int[] iconInts = new int[]{
            R.drawable.ic_action_name,
            R.drawable.ic_action_user,
            R.drawable.ic_action_password,
            R.drawable.ic_action_exit};

    private  String[] titleStrings = new String[]{
            "Title1",
            "Tile2",
            "Title3",
            "Exit"};

    public int[] getIconInts() {
        return iconInts;
    }

    public String[] getTitleStrings() {
        return titleStrings;
    }

    // About Url
    private String UrlAddUser = "http://androidthai.in.th/tod/addDataTod.php";
    private  String urlGetAllUser = "http://androidthai.in.th/tod/getAllUserTod.php";
    private String urlGetAllFood = "http://androidthai.in.th/tod/getAllFoodTod.php";


    public String getUrlGetAllFood() {
        return urlGetAllFood;
    }

    public String getUrlGetAllUser() {
        return urlGetAllUser;
    }

    public String getUrlAddUser() {
        return UrlAddUser;
    }
}
