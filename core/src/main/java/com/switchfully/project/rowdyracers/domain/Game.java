package com.switchfully.project.rowdyracers.domain;

import com.switchfully.project.rowdyracers.Controller;
import com.switchfully.project.rowdyracers.domain.actors.GameObject;
import com.switchfully.project.rowdyracers.domain.actors.Player;
import com.switchfully.project.rowdyracers.domain.actors.Trail;
import com.switchfully.project.rowdyracers.domain.actors.Wall;
import com.switchfully.project.rowdyracers.domain.world.Grid;

import java.util.Random;

public class Game {



    private final Controller parent;



    private final Grid grid;

    private final Player playerOne;

    private final Player playerTwo;

    private boolean playerOneTurn;

    private int movesLeft;

    private int wallsLeft;











    public Game(Controller parent){
        grid = new Grid(10);
        playerOne = new Player(true);
        playerTwo = new Player(false);
        wallsLeft = 20;
        generateWalls();
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

        if (checkPlayerHasWon(moving)) return;

        checkIfPlayerIsStuck();


    }

    private void checkEndTurn(){
        if(--movesLeft == 0) {
            playerOneTurn = !playerOneTurn;
            movesLeft = 3;
        }
    }



    private boolean checkIfMoveIsLegal(GameObject object, int x , int y){
        if(x > 9 || x < 0)return false;
        if(y > 9 || y < 0)return false;
        if(getObjectOnGrid(x,y) != null)return false;

        int objectX = grid.getObjectX(object);
        int objectY = grid.getObjectY(object);

        if(objectX+1 < x || objectX-1 > x) return false;
        if(objectY+1 < y || objectY-1 > y) return false;

        return !checkDiagonalTrail(objectX, objectY, x, y);

    }

    private boolean checkDiagonalTrail(int startX, int startY, int endX, int endY){

        if(getObjectOnGrid(startX,endY) instanceof Trail && getObjectOnGrid(endX,startY) instanceof Trail) return true;

        return false;
    }


    private void moveObject(GameObject object,int x, int y){
        grid.removeObject(object);

        grid.placeObjectAt(object,x,y);

    }




    public GameObject getObjectOnGrid(int x, int y){
        return grid.getGameObject(x,y);
    }

    private void trailCount(){
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


    private void generateWalls(){

        Random random = new Random();

        for (int vertical = 0; vertical < 10 ; vertical++) {
            for (int horizontal = 0; horizontal < 10; horizontal++) {

                if(wallsLeft == 0) return;

                if(!(vertical == 0 && horizontal == 9)&&!((vertical == 9 && horizontal == 0))){

                    if(random.nextInt(10) == 0) buildWall(random,vertical,horizontal);


                }

            }

        }






    }

    private void buildWall(Random random , int x, int y){
        int direction = random.nextInt(4);
        int length = random.nextInt(4) + 2;
        int crawlX = x;
        int crawlY = y;
        int placed = 0;

        while(length-- > 0){

            placeWall(crawlX,crawlY);
            placed++;

            switch(direction){

                case 0 -> crawlX++;
                case 1 -> crawlY++;
                case 2 -> crawlX--;
                case 3 -> crawlY--;

            }

            if(crawlX >= 9 || crawlX <= 0)break;
            if(crawlY >= 9 || crawlY <= 0)break;
            //if(crawlX == 9 && crawlY == 0)break;
            //if(crawlX == 0 && crawlY == 9)break;
            if(getObjectOnGrid(crawlX,crawlY) != null)break;

        }

        if (placed < 2) grid.removeObject(x,y);


    }


    public void placeWall(int x, int y){
        grid.placeObjectAt(new Wall(),x,y);
        wallsLeft--;

    }

    private boolean checkPlayerHasWon(Player player){
        if (player.isPLayerOne() && player.equals(grid.getGameObject(9,0))) {
            declareWinner(true);
            return true;
        }

        if (!player.isPLayerOne() && player.equals(grid.getGameObject(0,9))) {
            declareWinner(false);
            return true;
        }

        return false;
    }


    private void declareWinner(boolean winnerIsP1){

        playerOneTurn = winnerIsP1;

        String message;

        if (winnerIsP1) {
            message = "Player one wins!";
        } else message = "Player two wins!";

        parent.endGame(message);

    }

    private void checkIfPlayerIsStuck(){

        Player checked;
        if(playerOneTurn){
            checked = playerOne;
        } else{
            checked = playerTwo;
        }

        int vertical = grid.getObjectX(checked);
        int horizontal = grid.getObjectY(checked);

        if(checkIfMoveIsLegal(checked,vertical+1,horizontal)) return;
        if(checkIfMoveIsLegal(checked,vertical+1,horizontal+1)) return;
        if(checkIfMoveIsLegal(checked,vertical+1,horizontal-1)) return;
        if(checkIfMoveIsLegal(checked,vertical,horizontal+1)) return;
        if(checkIfMoveIsLegal(checked,vertical,horizontal-1)) return;
        if(checkIfMoveIsLegal(checked,vertical-1,horizontal)) return;
        if(checkIfMoveIsLegal(checked,vertical-1,horizontal+1)) return;
        if(checkIfMoveIsLegal(checked,vertical-1,horizontal-1)) return;

        declareWinner(!playerOneTurn);

    }




}
