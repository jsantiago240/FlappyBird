package com.santiago.javius.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.santiago.javius.flappybird.FlappyBird;
import com.santiago.javius.flappybird.Score;
import com.santiago.javius.flappybird.sprites.Bird;
import com.santiago.javius.flappybird.sprites.Ground;
import com.santiago.javius.flappybird.sprites.Tube;

import java.awt.Font;

public class PlayState extends State
{
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;

    private Ground myGround;

    private Score myScore;
    private int playerScore;

    private static Array<Tube> tubes;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        myScore = new Score();
        bird = new Bird(50,300);
        cam.setToOrtho(false, FlappyBird.WIDTH/2,FlappyBird.HEIGHT/2);
        bg = new Texture("bg.png");
        playerScore = 0;
        myGround = new Ground(cam);

        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++)
        {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            bird.jump();
        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();
        myGround.updateGround();

        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for(Tube tube : tubes)
        {
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth())
            {
                playerScore++;
                tube.reposition(tube.getPosBotTube().x +((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds()))
            {
                gsm.set(new GameOverState(gsm, playerScore));
            }


        }

        if (bird.getPosition().y <= myGround.getGround().getHeight() + myGround.GROUND_Y_OFFSET)
        {
            gsm.set(new GameOverState(gsm, playerScore));
        }

        cam.update();
    }


    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes)
        {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(myGround.getGround(), myGround.groundPos1.x, myGround.groundPos1.y);
        sb.draw(myGround.getGround(), myGround.groundPos2.x, myGround.groundPos2.y);

        sb.end();
    }

    @Override
    public void dispose()
    {
        bg.dispose();
        bird.dispose();
        myGround.dispose();

        for(Tube tube : tubes)
            tube.dispose();
    }

}
