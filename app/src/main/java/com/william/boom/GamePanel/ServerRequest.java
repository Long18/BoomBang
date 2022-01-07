package com.william.boom.GamePanel;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;
import com.william.boom.GameObject.Player;

/**
 * ServerRequest is the class to send information to server
 */
public class ServerRequest extends Thread {
    private HubConnection hubConnection;
    private Player player;

    private Context context;

    private float positionXSV, positionYSV;
    String abc = "asdas", bcd = "asdsd";

    public ServerRequest(Player player, Context context) {
        this.player = player;
        this.context = context;
    }

    public void update() {
        hubConnection = HubConnectionBuilder.create("http://thanhlongboom.somee.com/chatHub").build();

        positionXSV = (float) getPositionX();
        positionYSV = (float) getPositionY();

        hubConnection.on("ReceivePosition", (positionX, positionY) -> {
            try {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        positionXSV = positionX;
                        positionYSV = positionY;
                    }
                });
            } catch (Exception e) {
                Log.d("BUG:", e.getMessage());
            }

        }, Float.class, Float.class);

        new HubConnectionTask().execute(hubConnection);
    }

    public void send() {
        if (hubConnection.getConnectionState() == HubConnectionState.DISCONNECTED) {
            hubConnection.start();
        }

        if (hubConnection.getConnectionState() == HubConnectionState.CONNECTED) {
            hubConnection.send("ReceiveMessage", abc, bcd);
        }

        new HubConnectionTask().execute(hubConnection);
    }

    public double getPositionX() {
        return player.getPositionX();
    }

    public double getPositionY() {
        return player.getPositionY();
    }

    private static class HubConnectionTask extends AsyncTask<HubConnection, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(HubConnection... hubConnections) {
            HubConnection hubConnection = hubConnections[0];
            //hubConnection.start().blockingAwait();
            return null;
        }
    }
}
