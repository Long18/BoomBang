package com.william.boom.GamePanel;

import android.content.Context;
import android.util.Log;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;
import com.william.boom.GameObject.Player;

/**
 * ServerRequest is the class to send information to server
 */
public class ServerRequest {
    private HubConnection hubConnection;
    private Player player;
    private float positionXSV, positionYSV;

    public ServerRequest(Player player) {
        this.player = player;
    }

    public void sendToServer() {
        hubConnection = HubConnectionBuilder.create("https://10.0.2.2:7110/movehub").build();

        hubConnection.on("ReceivePosition", (positionX, positionY) -> {

            this.positionXSV = positionX;
            this.positionYSV = positionY;

        }, Float.class, Float.class);

        if (hubConnection.getConnectionState() == HubConnectionState.DISCONNECTED) {
            hubConnection.start();
        }

        if (hubConnection.getConnectionState() == HubConnectionState.CONNECTED) {
            hubConnection.send("PlayerMoveFromServer", player.getPositionX(), player.getPositionY());
        }
    }

    public double getPositionX() {
        return positionXSV;
    }

    public double getPositionY() {
        return positionYSV;
    }

}
