package game.GameObject.character.Ghosts;

import game.GameEngine.EnginesCaller;
import game.GameUtility.CharacterName;
import game.GameEngine.GameLogic;
import utility.GameObject;
import game.GameObject.character.Character;
import game.GameObject.character.PacMan;
import gamespace.Level;
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
    private int eatenSpeed = 15;


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
            initFrightenedBehaviour();
            return;
        }
        if(this.state == GhostState.TWINKLING){
            this.spriteIsBasedOnDir = false;
            initTwinklingBehaviour();
            return;
        }
        if(this.state == GhostState.CHASING || this.state == GhostState.DISPERSION){
            this.spriteIsBasedOnDir = true;
            initNormalBehaviour();
        }
        if(this.state == GhostState.EATEN){
            this.spriteIsBasedOnDir = false;
            initEatenBehaviour();
        }
        if(this.state == GhostState.REGENERATING){
            this.spriteIsBasedOnDir = true;
            this.setSpeed(normalSpeed);
        }
    }

    private void initTwinklingBehaviour() {
        setSprite(new ImageIcon(getClass().getResource("/VulnerableGhost/vulnerable2.gif")).getImage());
        this.setSpeed(frightenedSpeed);
    }

    private void initFrightenedBehaviour(){
        setSprite(new ImageIcon(getClass().getResource("/VulnerableGhost/vulnerable1.gif")).getImage());
        this.setDirection(EnginesCaller.getDirFromVector(EnginesCaller.getVectorFromDir(this.getDirection(),1).multiply(-1)));
        this.setSpeed(frightenedSpeed);
    }

    private void initEatenBehaviour(){
        setSprite(new ImageIcon(getClass().getResource("/VulnerableGhost/eaten.gif")).getImage());
        this.setSpeed(eatenSpeed);
    }

    public void initNormalBehaviour(){
        this.setSpeed(normalSpeed);
    }

    public abstract Vector2 calculateTarget(PacMan pacman, Level level, Vector2 blinkyPos);

    public void thinkNextDirection(Level level, Blinky blinky, List<Direction> directions){
        if(this.state == GhostState.REGENERATING) {//in this state, GameEngine control the ghost
            return;
        }
        Vector2 targetPos = this.getPosition();
        Direction oppositeOfCurrentDirection = EnginesCaller.getDirFromVector(EnginesCaller.getVectorFromDir(this.getDirection(),1).multiply(-1));
        directions.remove(oppositeOfCurrentDirection);
        if(this.state == GhostState.CHASING){
            targetPos = calculateTarget(GameLogic.pacMan, level,blinky.getPosition());
            this.setDirection(EnginesCaller.getDirReducingDist(this,targetPos,directions,this.getSpeed()));
        }else if(this.state == GhostState.DISPERSION) {
            targetPos = level.getSide(this.name);
            this.setDirection(EnginesCaller.getDirReducingDist(this,targetPos,directions,this.getSpeed()));
        }else if(this.state == GhostState.FRIGHTENED || this.state == GhostState.TWINKLING){
            targetPos = GameLogic.pacMan.getPosition();
            this.setDirection(EnginesCaller.getDirIncreasingDist(this,targetPos,directions,this.getSpeed()));
        }else if(this.state == GhostState.EATEN){
            targetPos = GameLogic.ghostBase.getBaseEntry();
            this.setDirection(EnginesCaller.getDirReducingDist(this,targetPos,directions,this.getSpeed()));
        }
        this.target = targetPos; //debugging
    }

}
