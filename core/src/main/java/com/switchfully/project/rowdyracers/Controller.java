package com.switchfully.project.rowdyracers;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.switchfully.project.rowdyracers.domain.Game;
import com.switchfully.project.rowdyracers.gui.ButtonAdder;
import com.switchfully.project.rowdyracers.gui.TextureHandler;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Controller extends ApplicationAdapter {
	private Stage stage;
	private Skin skin;


	private TextureHandler textures;

	private Table buttons;


	private Game game;

	@Override
	public void create() {
		stage = new Stage(new FitViewport(640, 480));
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		textures = new TextureHandler();
		game = new Game(this);

		buttons = ButtonAdder.addButtons(stage,game,textures);




		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render() {
		if(game.isPlayerOneTurn()){
			Gdx.gl.glClearColor(255f, 0f, 0f, 1f);
		}else{
			Gdx.gl.glClearColor(0f, 0f, 255f, 1f);
		}

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}


	public void updateButtons(){
		stage.getActors().removeValue(buttons,true);


		buttons = ButtonAdder.addButtons(stage,game,textures);
	}
}