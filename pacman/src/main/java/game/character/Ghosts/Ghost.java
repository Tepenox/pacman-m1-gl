package game.character.Ghosts;

import engines.IAEngine;
import engines.PhysicEngine;
import game.PacManGame;
import utility.GameObject;
import game.character.Character;
import game.character.PacMan;
import game.levels.Level;
import utility.Direction;
import utility.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Ghost extends Character {
    public Vector2 target;
    public GhostState state;

    public Ghost(int id, GhostState state, Vector2 position, Map<Direction, Image> sprites) {
        super(id,position,sprites);
        this.state = state;
    }

    @Override
    public void eat(GameObject character) {

    }

    public abstract Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos);

    public void thinkNextDirection(Level level, Blinky blinky, List<Direction> directions){
        Direction oppositeOfCurrentDirection = IAEngine.getDirFromVector(IAEngine.getVectorFromDir(this.getDirection(),1).multiply(-1));
        directions.remove(oppositeOfCurrentDirection);
        Vector2 targetPos = this.getPosition();
        if(this.state == GhostState.CHASING){
            targetPos = calculateTarget(PacManGame.pacMan,level,blinky.getPosition());
            this.setDirection(IAEngine.getDirReducingDist(this,targetPos,directions,this.getSpeed()));
        }
        this.target = targetPos; //debugging
    }

}
