package cn.nurasoft.bkhatfield.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import cn.nurasoft.bkhatfield.CommonAlpha.HelperClass;
import cn.nurasoft.bkhatfield.R;

/**
 * Implementation of App Widget functionality.
 */
public class RotaMinProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        HelperClass help=new HelperClass(context.getApplicationContext());

        CharSequence name = help.getDay();
        Log.e("Widget:",name.toString());
        CharSequence details= help.getRota();

        try {
            if (((String) details).contains("OFF")){
                details="OFF";
            }
        }catch (NullPointerException ex){
            Log.d("BKHatfield",ex.getMessage());
        }

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.rota_min_widget);
        views.setTextViewText(R.id.week_name,name);
        views.setTextViewText(R.id.rota_name, details);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {

        // See the dimensions and
        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);

        // Get min width and height.
        int minWidth = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        int minHeight = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);

        // Obtain appropriate widget and update it.
        appWidgetManager.updateAppWidget(appWidgetId, getRemoteViews(context, minWidth, minHeight));
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    private RemoteViews getRemoteViews(Context context, int minWidth, int minHeight) {
        // First find out rows and columns based on width provided.
        int rows = getCellsForSize(minHeight);
        Log.e("ROW:",rows+"");
        int columns = getCellsForSize(minWidth);
        Log.e("Column:",columns+"");
        // Now you changing layout base on you column count
        // In this code from 1 column to 4
        // you can make code for more columns on your own.
        switch (columns) {
            default: return new RemoteViews(context.getPackageName(), R.layout.rota_min_widget);
        }
    }

    /**
     * Returns number of cells needed for given size of the widget.
     *
     * @param size Widget size in dp.
     * @return Size in number of cells.
     */
    private static int getCellsForSize(int size) {
        int n = 2;
        while (70 * n - 30 < size) {
            ++n;
        }
        return n - 1;
    }
}

