package com.example.notificationsender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView Notifier ;

    private static final String CHANNEL_ID = "ChannelId";
    private static final int REQUEST_CODE = 1;
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Notifier = findViewById(R.id.Notifier);

        MyNotification();

        Notifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyNotification2();
            }
        });
    }

 private void MyNotification()
 {
     Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.largenotification,null);

     //converting drawable to BitmapDrawable
     BitmapDrawable bitmapDrawable =  (BitmapDrawable) drawable;
     //converting  BitmapDrawable to Bitmap
     assert bitmapDrawable != null;
     Bitmap largeIcon = bitmapDrawable.getBitmap();

     //creating intent to pass to the pendingIntent for shifting to another activity when user clicks on notification
     Intent IntentNotify = new Intent(getApplicationContext(),Activity2.class);

     NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
     Notification notification;

     //To clear Activity present in stack
     IntentNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

     PendingIntent  pendingIntent = PendingIntent.getActivity(this,REQUEST_CODE,IntentNotify,PendingIntent.FLAG_UPDATE_CURRENT);


     // Creating the custom notification layout
     RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.my_notification);
     notificationLayout.setTextViewText(R.id.notificationTitle, "Artificial Intelligence");
     notificationLayout.setTextViewText(R.id.notificationSummary, "AI on the Go!");


     if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
         notification = new Notification.Builder(this)
                 .setLargeIcon(largeIcon)
                 .setSmallIcon(R.drawable.notification_icon)
                 .setContentIntent(pendingIntent)
                 .setCustomContentView(notificationLayout)
                 .setChannelId(CHANNEL_ID)
                 .build();
         notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"Channel",NotificationManager.IMPORTANCE_HIGH));
     }
     else{
         notification = new Notification.Builder(this)
                 .setLargeIcon(largeIcon)
                 .setSmallIcon(R.drawable.notification_icon)
                 .setContentIntent(pendingIntent)
                 .setCustomContentView(notificationLayout)
                 .build();
     }

     notificationManager.notify(NOTIFICATION_ID,notification);
 }

 private void MyNotification2()
    {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.largenotification,null);

        //converting drawable to BitmapDrawable
        BitmapDrawable bitmapDrawable =  (BitmapDrawable) drawable;
        //converting  BitmapDrawable to Bitmap
        assert bitmapDrawable != null;
        Bitmap largeIcon = bitmapDrawable.getBitmap();

        //creating intent to pass to the pendingIntent for shifting to another activity when user clicks on notification
        Intent IntentNotify = new Intent(getApplicationContext(),Activity2.class);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;

        //To clear Activity present in stack
        IntentNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent  pendingIntent = PendingIntent.getActivity(this,REQUEST_CODE,IntentNotify,PendingIntent.FLAG_UPDATE_CURRENT);


        //Adding Style to the notification(Big Picture Style)
        Drawable drawable2 = ResourcesCompat.getDrawable(getResources(),R.drawable.ai_image,null);
        BitmapDrawable bitmapDrawable2 =  (BitmapDrawable) drawable2;
        //converting  BitmapDrawable to Bitmap
        assert bitmapDrawable2 != null;
        Bitmap largeIcon2 = bitmapDrawable2.getBitmap();
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .bigPicture(largeIcon2)
                .bigLargeIcon(largeIcon)
                .setBigContentTitle("Tap here")
                .setSummaryText("some information about AI");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("AI")
                    .setContentText("Unleash the Power of AI")
                    .setStyle(bigPictureStyle)
                    .setContentIntent(pendingIntent)
                    .setChannelId(CHANNEL_ID)
                    .build();
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"Channel",NotificationManager.IMPORTANCE_HIGH));
        }
        else{
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("AI")
                    .setContentText("Unleash the Power of AI")
                    .setStyle(bigPictureStyle)
                    .setContentIntent(pendingIntent)
                    .build();
        }

        notificationManager.notify(NOTIFICATION_ID,notification);
    }

}