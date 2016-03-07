package com.example.hp.zoomarket;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    TextView tvName, tvNumber, tvItemName, tvItemCost;
    DataBaseHelper db = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (TextView) findViewById(R.id.ordered_name);
        tvNumber = (TextView) findViewById(R.id.ordered_number);
        tvItemName = (TextView) findViewById(R.id.item_name);
        tvItemCost = (TextView) findViewById(R.id.item_cost);


        final Intent intent2 = new Intent(this, MakeOrder.class);
        btnAdd = (Button) findViewById(R.id.btn_add_new_order);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent2);

            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // запишем в лог значения requestCode и resultCode
        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode);

        // если пришло ОК
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constants.ORDER_ACTIVITY_CODE:
                    if (data.getExtras().getParcelable(Constants.NEW_ORDER).toString() !=null)
                    {
                        Toast.makeText(MainActivity.this, data.getExtras().getParcelable(Constants.NEW_ORDER).toString(), Toast.LENGTH_SHORT).show();

                        Order recieveOrder = data.getExtras().getParcelable(Constants.NEW_ORDER);
                        Log.d("Get DEBT in MAin", recieveOrder.toString());
                        DataBaseHelper db = new DataBaseHelper(this);

                        db.addOrder(recieveOrder);
                        Log.d("DataBase", db.getAllContacts().toString());
                        db.close();






                    }
                    else
                        Toast.makeText(MainActivity.this, "is null",Toast.LENGTH_SHORT).show();

                    break;


            }

            // если вернулось не ОК
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }

    }

    public void displayOrdersList() {

        List<Order> orders = db.getAllContacts();

        for (Order order : orders) {

            tvName.setText(order.getName());
            tvNumber.setText(order.getNumber());
            tvItemName.setText(order.getItemName());
            tvItemCost.setText(order.getCost());


        }

    }


}