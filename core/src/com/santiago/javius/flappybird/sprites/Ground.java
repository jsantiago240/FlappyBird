package com.santiago.javius.flappybird.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ground
{
    public static final int GROUND_Y_OFFSET = -50;

    public Vector2 groundPos1, groundPos2;

    private OrthographicCamera camera;
    private Texture ground;

    public Ground(OrthographicCamera cam)
    {
        camera = cam;
        ground = new Texture("ground.png");
        groundPos1 = new Vector2((cam.position.x - cam.viewportWidth / 2), GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
    }

    public void updateGround()
    {
        if(camera.position.x -(camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(camera.position.x -(camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }

    public Texture getGround()
    {
        return ground;
    }

    public void dispose()
    {
        ground.dispose();
    }
}
