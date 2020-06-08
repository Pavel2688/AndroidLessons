package user.pasha888.com.androidlessons;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BroadReceiver extends BroadcastReceiver {
    private final String TAG_LOG = "BroadCast";
    private static String CHANNEL_ID = "BirthDay";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG_LOG, "OnReceive context = " + context.toString());
        Log.d(TAG_LOG, "Receive action: " + intent.getAction());
        int id = intent.getIntExtra("id",0);
        String textReminder = intent.getStringExtra("textReminder");
        showNotification(context, textReminder, id);

    }

    private void showNotification(Context context, String text, int id) {
        Log.d(TAG_LOG, "SHOW");
        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.putExtra("contactDetail","details");
        resultIntent.putExtra("id",id);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(context,CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setContentTitle(context.getString(R.string.title_notification))
                        .setContentText(text)
                        .setChannelId(CHANNEL_ID)
                        .setContentIntent(resultPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "channelBirthDay", NotificationManager.IMPORTANCE_DEFAULT);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(1,  notification.build());

        repeatAlarm(context,id,text);
    }

    private void repeatAlarm(Context context,int id,String text) {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(ContactDetailsFragment.BROAD_ACTION);
        intent.putExtra("id",id);
        intent.putExtra("textReminder", text);

        Calendar birthOfDay = new GregorianCalendar();
        birthOfDay.add(Calendar.YEAR,1);

        PendingIntent alarmIntent = PendingIntent.getBroadcast(context,0,intent,0);
        alarmManager.set(AlarmManager.RTC, birthOfDay.getTimeInMillis(),alarmIntent);
    }
}
