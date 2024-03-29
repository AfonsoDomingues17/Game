package com.aor.pong.controller.game;

import com.aor.pong.Game;
import com.aor.pong.gui.GUI;
import com.aor.pong.model.Position;
import com.aor.pong.model.game.arena.Arena;
import com.aor.pong.model.game.elements.Ball;

import java.io.IOException;
import java.util.Random;

public class BallController extends GameController{
    private long lastMovement;
    public BallController(Arena arena){
        super(arena);
        this.lastMovement = 0;
    }
    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (time - lastMovement > 50) {
            moveBall(getModel().getBall(), getModel().getBall().getPosition().getDirection(getModel().getBall().getxVelocity(),getModel().getBall().getyVelocity()));
            this.lastMovement = time;
        }
    }
    public void moveBall(Ball ball, Position position) {

        if (getModel().checkGoal()) {
            ball.setPosition(new Position(getModel().getWidth()/2,getModel().getHeight()/2));
            Random random = new Random();

            ball.setxVelocity(random.nextInt(2));
            if(ball.getxVelocity() == 0)
                ball.setxVelocity(ball.getxVelocity()-1);

            ball.setyVelocity(random.nextInt(2));
            if(ball.getyVelocity() == 0)
                ball.setyVelocity(ball.getyVelocity() - 1);
        }
        else if (getModel().checkColisionPaddles()) {
            ball.setxVelocity(-(getModel().getBall().getxVelocity()));
        }
        else if (getModel().CheckBorderCollision()) {
            ball.setyVelocity(-getModel().getBall().getyVelocity());
        }
    }
}
