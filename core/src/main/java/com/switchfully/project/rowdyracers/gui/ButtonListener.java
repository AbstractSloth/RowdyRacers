package com.switchfully.project.rowdyracers.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.switchfully.project.rowdyracers.domain.Game;

public class ButtonListener extends ChangeListener {


    private final int buttonPosVertical;


    private final int buttonPosHorizontal;


    private final Game game;



    public ButtonListener(int buttonPosVertical, int buttonPosHorizontal, Game game) {
        this.buttonPosVertical = buttonPosVertical;
        this.buttonPosHorizontal = buttonPosHorizontal;
        this.game = game;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        game.registerGridInput(buttonPosVertical,buttonPosHorizontal);
    }


    public int getVertical() {
        return buttonPosVertical;
    }

    public int getHorizontal() {
        return buttonPosHorizontal;
    }
}
