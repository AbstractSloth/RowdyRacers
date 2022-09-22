package com.switchfully.project.rowdyracers.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.switchfully.project.rowdyracers.domain.Game;

import java.util.ArrayList;

public abstract class ButtonAdder {


    public static Table addButtons(Stage stage, Game game,TextureHandler textures){

        ArrayList<ImageButton> buttons = new ArrayList<>();

        int size = game.getGrid().getGridSize();

        Table table = new Table();
        table.setFillParent(true);


        for (int vertical = 0; vertical < size ; vertical++) {
            for (int horizontal = 0; horizontal < size; horizontal++) {
                ImageButton button = new ImageButton(textures.getCorrectSprite(game.getObjectOnGrid(vertical,horizontal)));
                button.setSize(stage.getHeight()/size, stage.getHeight()/size);

                System.out.println(button.getHeight());


                button.addListener(new ButtonListener(vertical,horizontal,game));
                table.add(button).fillX().uniformX();
                buttons.add(button);



            }
            table.row();
        }
        stage.addActor(table);
        System.out.println(table.getHeight());

        return table;

    }
}
