package com.santiago.javius.flappybird.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.santiago.javius.flappybird.FlappyBird;
import com.santiago.javius.flappybird.Score;
import com.santiago.javius.flappybird.sprites.Ground;

import sun.rmi.runtime.Log;

public class GameOverState extends State
{
    private Texture bg;
    private Texture gameOver;
    private Texture replayBtn;
    private Ground myGround;

    protected GameOverState(GameStateManager gsm, int score)
    {
        super(gsm);

        FlappyBird.stopMusic();
        cam.setToOrtho(false, FlappyBird.WIDTH/2,FlappyBird.HEIGHT/2);
        myGround = new Ground(cam);
        bg = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
        replayBtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput()
    {
        if (Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));
            FlappyBird.startMusic();
            dispose();
        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(replayBtn, cam.position.x - replayBtn.getWidth() / 2, cam.position.y - replayBtn.getHeight());
        sb.draw(gameOver, cam.position.x - gameOver.getWidth() / 2, cam.position.y + gameOver.getHeight());
        sb.draw(myGround.getGround(), myGround.groundPos1.x, myGround.groundPos1.y);
        sb.end();
    }

    @Override
    public void dispose()
    {
        myGround.dispose();
        bg.dispose();
        gameOver.dispose();
        replayBtn.dispose();
    }
}
