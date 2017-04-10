package com.example.android.inventoryapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.ProductContract.ProductEntry;

public class DetailsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * Adapter for the ListView
     */
    DetailedProductCursorAdapter mDetailedCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRODUCT_BRAND,
                ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductEntry.COLUMN_PRODUCT_IMAGE,
                ProductEntry.COLUMN_SUPPLIER_NAME,
                ProductEntry.COLUMN_SUPPLIER_PHONE,
                ProductEntry.COLUMN_SUPPLIER_EMAIL
        };

        Cursor cursor = getContentResolver().query(ProductEntry.CONTENT_URI, projection, null, null, null);

        // Find the ListView which will be populated with the pet data
        ListView productDetailListView = (ListView) findViewById(R.id.detail_list_item);

        // Setup an Adapter to create a list item for each row of pet data in the Cursor.

        mDetailedCursorAdapter = new DetailedProductCursorAdapter(this, cursor);
        productDetailListView.setAdapter(mDetailedCursorAdapter);

    }

    // Decrease quantity by 1
    Button btnTrack = (Button) findViewById(R.id.track_sale_button);
        btnTrack.setOnClickListener(new View.OnClickListener()  {
        @Override
        public void onClick (View view){
            if (cursor.moveToFirst()) {
                int quantity = cursor.getInt(cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY));
                if (quantity > 0) {
                    db.updateData(subProductName, quantity, -1);
                    quantity = cursor.getInt(cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY));
                    mDetailQuantity = (TextView) findViewById(R.id.detail_quantity);
                    mDetailQuantity.setText("" + quantity);
                    Toast.makeText(DetailsActivity.this, "Refresh!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailsActivity.this, "It's empty! Order Now!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // Increase quantity by 1
    Button btnReceive = (Button) findViewById(R.id.received_shipment_button);
        btnReceive.setOnClickListener(new View.OnClickListener()    {
        @Override
        public void onClick (View view){
        if (cursor.moveToFirst()) {
            int quantity = cursor.getInt(cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY));
            db.updateData(subProductName, quantity, 1);
            quantity = cursor.getInt(cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY));
            mDetailQuantity = (TextView) findViewById(R.id.text_quantity);
            mDetailQuantity.setText("" + quantity);
            Toast.makeText(DetailsActivity.this, "Refresh!", Toast.LENGTH_SHORT).show();
        }
    }

        // Order Now
        Button orderNow = (Button) findViewById(R.id.order_stock);
        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = "";
                if (cursor.moveToFirst()) {
                    productName = cursor.getString(cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME));
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_TEXT, "In need of some " + productName);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        };

        // delete row
        Button delete = (Button) findViewById(R.id.delete_product_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                db.delete(ProductEntry.TABLE_NAME);
                                    Intent returnHome = new Intent(DetailsActivity.this, InventoryActivity.class);
                                    startActivity(returnHome);
                                    Toast.makeText(DetailsActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder alert = new AlertDialog.Builder(DetailsActivity.this);
                alert.setMessage("Are you sure you want to delete?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        // Convert byte array to bitmap and display the image
        ImageView img = (ImageView) findViewById(R.id.imageView);
        byte[] image = cursor.getBlob(cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_IMAGE));
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        img.setImageBitmap(bitmap);
    }
