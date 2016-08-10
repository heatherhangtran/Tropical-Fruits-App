package com.example.randybiglow.tropical_fruits_app;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HangTran on 5/13/16.
 */
public class CustomCursorAdapter extends CursorAdapter {
    LayoutInflater cursorInflater;

    public CustomCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName = (TextView) view.findViewById(R.id.commonNameText);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageIcon);
        String name = cursor.getString(cursor.getColumnIndex(FruitDatabaseHelper.COL_COMMON_NAME));
        String image = cursor.getString(cursor.getColumnIndex(FruitDatabaseHelper.COL_IMAGE));
        textViewName.setText(name);
        imageView.setImageResource(Integer.parseInt(image));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.list_item, parent, false);
    }
}
