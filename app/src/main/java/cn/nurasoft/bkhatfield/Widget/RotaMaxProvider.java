package cn.nurasoft.bkhatfield.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import cn.nurasoft.bkhatfield.R;


public class RotaMaxProvider  extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        SharedPreferences preferences=context.getSharedPreferences("config",Context.MODE_PRIVATE);
//
//
//        if (((String) details).contains("OFF")){
//            details="OFF";
//        }

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.rota_max_widget);

        views.setTextViewText(R.id.rota_monday,preferences.getString("Monday","N/A"));
        views.setTextViewText(R.id.rota_tuesday,preferences.getString("Tuesday","N/A"));
        views.setTextViewText(R.id.rota_wednesday,preferences.getString("Wednesday","N/A"));
        views.setTextViewText(R.id.rota_thursday,preferences.getString("Thursday","N/A"));
        views.setTextViewText(R.id.rota_friday,preferences.getString("Friday","N/A"));
        views.setTextViewText(R.id.rota_saturday,preferences.getString("Saturday","N/A"));
        views.setTextViewText(R.id.rota_sunday,preferences.getString("Sunday","N/A"));

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
      //  appWidgetManager.updateAppWidget(appWidgetId, getRemoteViews(context, minWidth, minHeight));
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }
}
