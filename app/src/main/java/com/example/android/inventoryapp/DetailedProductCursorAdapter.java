package com.example.android.inventoryapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.inventoryapp.data.ProductContract.ProductEntry;

/**
 * This adapter knows how to create list items for each row of product data in the cursor}.
 */
public class DetailedProductCursorAdapter extends CursorAdapter {
    public DetailedProductCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    private TextView mDetailName;

    private TextView mDetailBrand;

    private TextView mDetailPrice;

    private TextView mDetailQuantity;

    private ImageView mDetailImage;

    private TextView mDetailSupplierName;

    private TextView mDetailSupplierPhone;

    private TextView mDetailSupplierEmail;

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.detail_layout, parent, false);
    }

    /**
     * This method binds the product data (in the current row pointed to by cursor) to the given list item layout.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find all relevant views that we will need to read user input from
        mDetailName = (TextView) view.findViewById(R.id.detail_name);
        mDetailBrand = (TextView) view.findViewById(R.id.detail_brand);
        mDetailPrice = (TextView) view.findViewById(R.id.detail_price);
        mDetailQuantity = (TextView) view.findViewById(R.id.detail_quantity);
        mDetailImage = (ImageView) view.findViewById(R.id.detail_image);
        mDetailSupplierName = (TextView) view.findViewById(R.id.detail_supplier_name);
        mDetailSupplierPhone = (TextView) view.findViewById(R.id.detail_supplier_phone);
        mDetailSupplierEmail = (TextView) view.findViewById(R.id.detail_supplier_email);

        int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
        int brandColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_BRAND);
        int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int imageColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_IMAGE);
        int supplierNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_NAME);
        int supplierPhoneColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_PHONE);
        int supplierEmailColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_EMAIL);

        // Extract out the value from the Cursor for the given column index
        String name = cursor.getString(nameColumnIndex);
        String brand = cursor.getString(brandColumnIndex);
        int price = cursor.getInt(priceColumnIndex);
        int quantity = cursor.getInt(quantityColumnIndex);
        String image = cursor.getString(imageColumnIndex);
        String supplierName = cursor.getString(supplierNameColumnIndex);
        String supplierPhone = cursor.getString(supplierPhoneColumnIndex);
        String supplierEmail = cursor.getString(supplierEmailColumnIndex);

        mDetailName.setText(name);
        mDetailBrand.setText(brand);
        mDetailPrice.setText(Integer.toString(price));
        mDetailQuantity.setText(Integer.toString(quantity));
        mDetailImage.setImage(image);
        mDetailSupplierName.setText(supplierName);
        mDetailSupplierPhone.setText(supplierPhone);
        mDetailSupplierEmail.setText(supplierEmail);
    }
}