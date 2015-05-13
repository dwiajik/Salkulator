package com.salkulator.dwiajik.salkulator;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends ActionBarActivity {

    Button btnAdd, btnReset;
    EditText txtItem, txtPrice, txtDiscount;
    TextView txtTotal;
    LinearLayout listItem;
    ArrayList<Item> itemList = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.buttonTambah);
        btnAdd.setOnClickListener(buttonAddClick);

        btnReset = (Button) findViewById(R.id.buttonHapus);
        btnReset.setOnClickListener(buttonResetClick);

        txtItem = (EditText) findViewById(R.id.editTextNamaBarang);
        txtPrice = (EditText) findViewById(R.id.editTextHarga);
        txtDiscount = (EditText) findViewById(R.id.editTextDiskon);

        txtTotal = (TextView) findViewById(R.id.textTotal);

        listItem = (LinearLayout) findViewById(R.id.listItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener buttonAddClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double total = 0;

            Item item = new Item();

            try {
                item.setName(txtItem.getText().toString());
                item.setPrice(Double.parseDouble(txtPrice.getText().toString()));
                item.setDiscount(Double.parseDouble(txtDiscount.getText().toString()) * 0.01);
                itemList.add(item);
            }
            catch (Exception e)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Kesalahan!");
                alertDialog.setMessage("Kesalahan pada harga atau diskon!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        return;
                    }
                });
                alertDialog.show();
                return;
            }

            if(item.getPrice() < 0 || item.getDiscount() < 0 || item.getDiscount() > 1)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Kesalahan!");
                alertDialog.setMessage("Kesalahan pada harga atau diskon!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        return;
                    }
                });
                alertDialog.show();
                return;
            }

            for(int i = 0; i < itemList.size(); i++)
            {
                total += itemList.get(i).getTotal();
            }

            LinearLayout ly = new LinearLayout(v.getContext());
            ly.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams oneWidth = new LinearLayout.LayoutParams(0, 100);
            oneWidth.weight = 1;

            TextView name = new TextView(v.getContext());
            name.setText(item.getName());
            name.setLayoutParams(oneWidth);
            ly.addView(name);

            TextView price = new TextView(v.getContext());
            price.setText("Rp " + String.valueOf((int) Math.round(item.getPrice())));
            price.setLayoutParams(oneWidth);
            ly.addView(price);

            TextView discount = new TextView(v.getContext());
            discount.setText(String.valueOf((int) Math.round(item.getDiscount()*100)) + "%");
            discount.setLayoutParams(oneWidth);
            ly.addView(discount);

            TextView subtotal = new TextView(v.getContext());
            subtotal.setText("Rp " + String.valueOf((int) Math.round(item.getTotal())));
            subtotal.setLayoutParams(oneWidth);
            ly.addView(subtotal);

            listItem.addView(ly);

            txtTotal.setText("Total: Rp " + String.valueOf((int) Math.round(total)));

            txtItem.setText("");
            txtPrice.setText("");
            txtDiscount.setText("");
        }
    };


    View.OnClickListener buttonResetClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listItem.removeAllViews();
            itemList.clear();
            txtItem.setText("");
            txtPrice.setText("");
            txtDiscount.setText("");
            txtTotal.setText("");
        }
    };
}
