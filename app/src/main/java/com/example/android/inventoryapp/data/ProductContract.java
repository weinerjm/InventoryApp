package com.example.android.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API Contract for the Inventory App
 */

public class ProductContract {

    private ProductContract()   {}

    public static final String CONTENT_AUTHORITY = "com.example.android.inventoryapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_INVENTORY = "inventory";


    /** Inner class that defines constant values for the inventory database table
     * Each entry in the table will represent a single product
     */
    public static final class ProductEntry implements BaseColumns{

        /**
         * Full content Uri to access the inventory data
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        /**
         * The MIME type of the content Uri for a list of products
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        /**
         * The MIME type of the content Uri for a single product
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        /**
         * Information contained within the database table:
         */
        public final static String TABLE_NAME = "inventory";

        /**
         * Unique ID for each product
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of each product
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_NAME = "name";

        /**
         * Brand of each product
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_BRAND = "brand";

        /**
         * Price for one unit of each product
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_PRICE = "price";

        /**
         * Quantity in stock of each product
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_QUANTITY = "quantity";

        /**
         * Photo representing each product
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_IMAGE = "image";

        /**
         * Supplier information
         * Type: TEXT
         */
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";
        public static final String COLUMN_SUPPLIER_EMAIL = "supplier_email";
    }
}
