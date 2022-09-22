package com.switchfully.project.rowdyracers.domain;

import com.switchfully.project.rowdyracers.Controller;
import com.switchfully.project.rowdyracers.domain.actors.GameObject;
import com.switchfully.project.rowdyracers.domain.actors.Player;
import com.switchfully.project.rowdyracers.domain.actors.Trail;
import com.switchfully.project.rowdyracers.domain.world.Grid;

public class Game {



    private final Controller parent;



    private final Grid grid;

    private final Player playerOne;

    private final Player playerTwo;

    private boolean playerOneTurn;

    private int movesLeft;











    public Game(Controller parent){
        grid = new Grid(10);
        playerOne = new Player(true);
        playerTwo = new Player(false);
        grid.placeObjectAt(playerOne,0,9);
        grid.placeObjectAt(playerTwo,9,0);
        playerOneTurn = true;
        movesLeft = 3;

        this.parent = parent;
    }


    public Grid getGrid() {
        return grid;
    }


    public boolean isPlayerOneTurn() {
        return playerOneTurn;
    }

    public void registerGridInput(int x, int y){

        System.out.println("height " + x + " width " + y);

        playerMove(x,y);

    }


    private void playerMove(int x, int y){

        Player moving;
        if(playerOneTurn){
            moving = playerOne;
        } else{
            moving = playerTwo;
        }
        if(!checkIfMoveIsLegal(moving,x,y))return;

        int oldX = grid.getObjectX(moving);
        int oldY = grid.getObjectY(moving);

        moveObject(moving,x,y);
        grid.placeObjectAt(new Trail(playerOneTurn),oldX,oldY);
        trailCount();
        checkEndTurn();
        parent.updateButtons();

    }

    private void checkEndTurn(){
        if(--movesLeft == 0) {
            playerOneTurn = !playerOneTurn;
            movesLeft = 3;
        }
    }



    private boolean checkIfMoveIsLegal(GameObject object, int x , int y){
        if(getObjectOnGrid(x,y) != null)return false;

        int objectX = grid.getObjectX(object);
        int objectY = grid.getObjectY(object);

        if(objectX+1 < x || objectX-1 > x) return false;
        if(objectY+1 < y || objectY-1 > y) return false;

        return true;

    }


    private void moveObject(GameObject object,int x, int y){
        grid.removeObject(object);

        grid.placeObjectAt(object,x,y);

    }




    public GameObject getObjectOnGrid(int x, int y){
        return grid.getGameObject(x,y);
    }

    public void trailCount(){
        for (int vertical = 0; vertical < 10 ; vertical++) {
            for (int horizontal = 0; horizontal < 10; horizontal++) {

                GameObject object = getObjectOnGrid(vertical,horizontal);

                if ( object instanceof Trail
                        && ((Trail) object).isBelongsToPlayerOne() == playerOneTurn
                        && ((Trail) object).countDown())
                    grid.removeObject(vertical,horizontal);

            }

        }
    }


}
