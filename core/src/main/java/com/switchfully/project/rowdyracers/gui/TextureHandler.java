package com.switchfully.project.rowdyracers.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.switchfully.project.rowdyracers.domain.Game;
import com.switchfully.project.rowdyracers.domain.actors.GameObject;
import com.switchfully.project.rowdyracers.domain.actors.Player;
import com.switchfully.project.rowdyracers.domain.actors.Trail;

public class TextureHandler {

    private final Sprite empty;
    private final Sprite playerOne;

    private final Sprite playerTwo;

    private final Sprite trail;


    public TextureHandler() {
        this.empty = new Sprite(new Texture("sprites/empty.png"));
        this.playerOne = new Sprite(new Texture("sprites/player1.png"));
        this.playerTwo = new Sprite(new Texture("sprites/player2.png"));
        this.trail = new Sprite(new Texture("sprites/trail.png"));
    }



    public SpriteDrawable getCorrectSprite(GameObject object){
        if(object == null)
            return new SpriteDrawable(empty);

        if(object instanceof Trail)return new SpriteDrawable(trail);

        if(object instanceof Player && ((Player) object).isPLayerOne())return new SpriteDrawable(playerOne);
        return new SpriteDrawable(playerTwo);
    }
}
