package com.william.boom.GameObject;
/**
 * EnemyState to check sprite of enemy
 */
public class EnemyState {

    public enum State{
        NOT_MOVING,
        STARED_MOVING,
        IS_MOVING,
        IS_DEAD

    }

    private Enemy enemy;
    private State state;

    public EnemyState(Enemy enemy){
        this.enemy = enemy;
        this.state = State.NOT_MOVING;
    }

    public State getState(){
        return state;
    }

    public void update(){
        switch (state){
            case NOT_MOVING:
                if (enemy.velocityX != 0 || enemy.velocityY != 0){
                    state = State.STARED_MOVING;
                }
                break;
            case STARED_MOVING:
                if (enemy.velocityX != 0 || enemy.velocityY != 0){
                    state = State.IS_MOVING;
                }
                break;
            case IS_MOVING:
                if (enemy.velocityX == 0 && enemy.velocityY ==0){
                    state = State.NOT_MOVING;
                }
                break;
            default:
                break;
        }
    }
}
