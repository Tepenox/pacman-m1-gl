package game.character.Ghosts;

import game.GameEngine.Engines;
import game.GameUtility.CharacterName;
import game.GameEngine.GameLogic;
import utility.GameObject;
import game.character.Character;
import game.character.PacMan;
import game.levels.Level;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public abstract class Ghost extends Character {
    public Vector2 target = new Vector2(0,0);
    public GhostState state;

    private int normalSpeed = super.getSpeed();
    private int frightenedSpeed = 3;
    private int eatenSpeed = 12;


    public Ghost(int id, GhostState state, Vector2 position, Map<Direction, Image> sprites,CharacterName name) {
        super(id,position,sprites,name);
        this.state = state;
        this.setSpeed(normalSpeed);
    }

    @Override
    public void eat(GameObject character) {

    }

    public void setState(GhostState state) {
        this.state = state;
        setSpriteAndBehaviour();
    }

    private void setSpriteAndBehaviour() {
        if(this.state == GhostState.FRIGHTENED){
            this.spriteIsBasedOnDir = false;
            initFrightened();
            return;
        }
        if(this.state == GhostState.TWINKLING){
            this.spriteIsBasedOnDir = false;
            initTwinkling();
            return;
        }
        if(this.state == GhostState.CHASING || this.state == GhostState.DISPERSION){
            this.spriteIsBasedOnDir = true;
            initNormalBehaviour();
        }
    }

    private void initTwinkling() {
        setSprite(new ImageIcon(getClass().getResource("/VulnerableGhost/vulnerable2.gif")).getImage());
        this.setSpeed(frightenedSpeed);
    }

    private void initFrightened(){
        setSprite(new ImageIcon(getClass().getResource("/VulnerableGhost/vulnerable1.gif")).getImage());
        this.setDirection(Engines.getDirFromVector(Engines.getVectorFromDir(this.getDirection(),1).multiply(-1)));
        this.setSpeed(frightenedSpeed);
    }

    private void initNormalBehaviour(){
        super.setDirection(getDirection());
        this.setSpeed(normalSpeed);
    }

    public abstract Vector2 calculateTarget(PacMan pacman, Level level, Vector2 blinkyPos);

    public void thinkNextDirection(Level level, Blinky blinky, List<Direction> directions){
        Direction oppositeOfCurrentDirection = Engines.getDirFromVector(Engines.getVectorFromDir(this.getDirection(),1).multiply(-1));
        directions.remove(oppositeOfCurrentDirection);
        Vector2 targetPos = this.getPosition();
        if(this.state == GhostState.CHASING){
            targetPos = calculateTarget(GameLogic.pacMan,level,blinky.getPosition());
            this.setDirection(Engines.getDirReducingDist(this,targetPos,directions,this.getSpeed()));
        }else if(this.state == GhostState.DISPERSION) {
            targetPos = level.getSide(this.name);
            this.setDirection(Engines.getDirReducingDist(this,targetPos,directions,this.getSpeed()));
        }else if(this.state == GhostState.FRIGHTENED || this.state == GhostState.TWINKLING){
            targetPos = GameLogic.pacMan.getPosition();
            this.setDirection(Engines.getDirIncreasingDist(this,targetPos,directions,this.getSpeed()));
        }
        this.target = targetPos; //debugging
    }

}
