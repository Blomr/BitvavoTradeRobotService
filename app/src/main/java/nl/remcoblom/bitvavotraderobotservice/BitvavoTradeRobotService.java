package nl.remcoblom.bitvavotraderobotservice;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import nl.remcoblom.bitvavotraderobot.BitvavoRobot;

public class BitvavoTradeRobotService extends IntentService {

    public BitvavoTradeRobotService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        BitvavoRobot robot = new BitvavoRobot(30);
        robot.start();
        while(true) {
            if (robot.getMyEUR().getAmount() > 0) {
                //NotificationChannel channel = new NotificationChannel("CUSTOM_CHANNEL", "CUSTOM_CHANNEL", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CUSTOM_CHANNEL")
                        .setContentTitle("Bitvavo Robot")
                        .setContentText("EUR balance: " + robot.getMyEUR().getAmount())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat manager = NotificationManagerCompat.from(this);
                manager.notify(robot.hashCode(), builder.build());
            }
        }
    }
}
