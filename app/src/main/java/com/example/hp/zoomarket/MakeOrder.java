package com.example.hp.zoomarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by HP on 29.02.2016.
 */
public class MakeOrder extends AppCompatActivity {
    EditText ordName, ordNum, itmName, itmCost;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DataBaseHelper db = new DataBaseHelper(this);
        setContentView(R.layout.make_order_layout);
        final Bundle bundle = new Bundle();
        final Intent intent = new Intent(this,MainActivity.class);
        ordName = (EditText) findViewById(R.id.orderName);
        ordNum = (EditText) findViewById(R.id.orderNumber);
        itmName = (EditText) findViewById(R.id.itemName);
        itmCost = (EditText) findViewById(R.id.itemCost);

        addBtn = (Button) findViewById(R.id.btnAdd);

        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                Order order = new Order(ordName.getText().toString(), Integer.parseInt(ordNum.getText().toString()), itmName.getText().toString(),Integer.parseInt(itmCost.getText().toString()));


                bundle.putParcelable(Constants.NEW_ORDER, order);
                intent.putExtras(bundle);

                setResult(Activity.RESULT_OK, intent);
                Log.d("intent",intent.getExtras().toString());
                Log.d("MakeOrder.class",order.toString());
                finish();


            }});


    }}







