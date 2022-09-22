package com.switchfully.project.rowdyracers.domain.world;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.switchfully.project.rowdyracers.domain.actors.GameObject;
import com.switchfully.project.rowdyracers.gui.ButtonListener;

public class Grid {


    private final GameObject[][] gameGrid;

    public Grid(int size) {
        this.gameGrid = new GameObject[size][size];
    }


    public int getGridSize(){
        return gameGrid.length;
    }


    public GameObject getGameObject(int x , int y){
        return gameGrid[x][y];
    }

    public void placeObjectAt(GameObject object,int x , int y){
        gameGrid[x][y] = object;
    }

    public void removeObject(GameObject object){

        for (int vertical = 0; vertical < gameGrid.length ; vertical++) {
            for (int horizontal = 0; horizontal < gameGrid.length; horizontal++) {
                if (object.equals(gameGrid[vertical][horizontal])) gameGrid[vertical][horizontal] = null;

            }

        }
    }

    public void removeObject(int x, int y){

        if(x <0 || x >= gameGrid.length) return;
        if(y <0 || y >= gameGrid.length) return;

        gameGrid[x][y] = null;
    }


    public int getObjectX(GameObject object){
        for (int vertical = 0; vertical < gameGrid.length ; vertical++) {
            for (int horizontal = 0; horizontal < gameGrid.length; horizontal++) {
                if (object.equals(gameGrid[vertical][horizontal])) return vertical;

            }

        }
        return -1;
    }


    public int getObjectY(GameObject object){
        for (int vertical = 0; vertical < gameGrid.length ; vertical++) {
            for (int horizontal = 0; horizontal < gameGrid.length; horizontal++) {
                if (object.equals(gameGrid[vertical][horizontal])) return horizontal;

            }

        }
        return -1;
    }



}
