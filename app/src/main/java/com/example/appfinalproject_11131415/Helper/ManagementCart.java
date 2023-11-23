package com.example.appfinalproject_11131415.Helper;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfinalproject_11131415.Domain.PopularDomain;
import java.util.ArrayList;

public class ManagementCart extends AppCompatActivity {
    private Context context;
    private TinyDB tinyDB;
     public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertFood(PopularDomain item){
        ArrayList<PopularDomain> listpop = getlistCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listpop.size(); i++) {
            if (listpop.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }
        if (existAlready){
            listpop.get(n).setNumberCart(item.getNumberCart());
        }else {
            listpop.add(item);
        }
        tinyDB.putListObject("CartList", listpop);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
     }
    private ArrayList<PopularDomain> getlistCart() {
        return tinyDB.getListObject("CartList");
     }

}
