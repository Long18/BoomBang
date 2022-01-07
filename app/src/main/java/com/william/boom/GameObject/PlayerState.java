package com.william.boom.GameObject;


/**
 * PlayerState to check sprite of character
 */
public class PlayerState {

    public enum State {
        NOT_MOVING,
        STARED_MOVING,
        IS_MOVING,
        MOVING_RIGHT,
        MOVING_LEFT,
        MOVING_UP
    }

    private Player player;
    private State state;

    public PlayerState(Player player) {
        this.player = player;
        this.state = State.NOT_MOVING;
    }

    public State getState() {
        return state;
    }

    public void update() {
        switch (state) {
            case NOT_MOVING:
                if (player.velocityX != 0 || player.velocityY != 0) {
                    state = State.STARED_MOVING;
                }
                break;
            case STARED_MOVING:
                if (player.velocityX != 0 || player.velocityY != 0) {
                    state = State.IS_MOVING;
                }
                break;
            case IS_MOVING:
                if (player.velocityX == 0 && player.velocityY == 0) {
                    state = State.NOT_MOVING;
                }

                if (player.velocityX > 0) {
                    state = State.MOVING_RIGHT;
                }

                if (player.velocityX < 0) {
                    state = State.MOVING_LEFT;
                }

                if (player.velocityY < 0) {
                    state = State.MOVING_UP;
                }
                break;
            case MOVING_LEFT:
                if (player.velocityX == 0 && player.velocityY == 0) {
                    state = State.NOT_MOVING;
                }

                if (player.velocityX > 0) {
                    state = State.MOVING_RIGHT;
                }

                if (player.velocityY < 0) {
                    state = State.MOVING_UP;
                }
                break;
            case MOVING_RIGHT:
                if (player.velocityX == 0 && player.velocityY == 0) {
                    state = State.NOT_MOVING;
                }


                if (player.velocityX < 0) {
                    state = State.MOVING_LEFT;
                }

                if (player.velocityY < 0) {
                    state = State.MOVING_UP;
                }
                break;
            case MOVING_UP:

                if (player.velocityX == 0 && player.velocityY == 0) {
                    state = State.NOT_MOVING;
                }

                if (player.velocityX > 0) {
                    state = State.MOVING_RIGHT;
                }

                if (player.velocityX < 0) {
                    state = State.MOVING_LEFT;
                }

                break;
            default:
                break;
        }
    }
}
