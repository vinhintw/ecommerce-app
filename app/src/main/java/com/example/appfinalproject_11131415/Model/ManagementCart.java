package com.example.appfinalproject_11131415.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfinalproject_11131415.Domain.PopularDomain;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ManagementCart extends AppCompatActivity {
    private Context context;
    private SharedPreferences sharedPreferences;
    private static final String CART_PREFERENCES = "CartPreferences";

    public ManagementCart(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(CART_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void insertFood(PopularDomain item) {
        ArrayList<PopularDomain> listpop = getlistCart();

        if (listpop == null) {
            listpop = new ArrayList<>();
        }

        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listpop.size(); i++) {
            if (listpop.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listpop.get(n).setNumberCart(item.getNumberInCart());
        } else {
            listpop.add(item);
        }

        saveListToPreferences("CartList", listpop);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }


    public ArrayList<PopularDomain> getlistCart() {
        return getListFromPreferences("CartList");
    }

    public Double getTotalFee() {
        ArrayList<PopularDomain> listItem = getlistCart();
        double fee = 0;
        for (int i = 0; i < listItem.size(); i++) {
            fee = fee + (listItem.get(i).getPrice() * listItem.get(i).getNumberInCart());
        }
        return fee;
    }

    public void minusNumberItem(ArrayList<PopularDomain> listItem, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listItem.get(position).getNumberInCart() == 1) {
            listItem.remove(position);
        } else {
            listItem.get(position).setNumberInCart((listItem.get(position).getNumberInCart() - 1));
        }
        saveListToPreferences("CartList", listItem);
        changeNumberItemsListener.change();
    }

    public void plusNumberItem(ArrayList<PopularDomain> listItem, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart() + 1);
        saveListToPreferences("CartList", listItem);
        changeNumberItemsListener.change();
    }

    private void saveListToPreferences(String key, ArrayList<PopularDomain> list) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    private ArrayList<PopularDomain> getListFromPreferences(String key) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);
        Type type = new TypeToken<ArrayList<PopularDomain>>() {}.getType();
        return gson.fromJson(json, type);
    }
}