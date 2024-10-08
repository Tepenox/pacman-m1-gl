package game.GameObject.character.Ghosts;

import game.GameEngine.EnginesCaller;
import game.GameObject.character.PacMan;
import game.gamespace.Level;
import game.GameUtility.CharacterName;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Clyde extends Ghost{                       //le fantôme orange.
    public Clyde(Vector2 position) {
        super(7,GhostState.DISPERSION,position,new HashMap<>(),CharacterName.CLYDE);
        this.sprites.put(Direction.UP,new ImageIcon(getClass().getResource("/Clyde/upClyde.gif")).getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon(getClass().getResource("/Clyde/downClyde.gif")).getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon(getClass().getResource("/Clyde/rightClyde.gif")).getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon(getClass().getResource("/Clyde/leftClyde.gif")).getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon(getClass().getResource("/Clyde/leftClyde.gif")).getImage());
        setDirection(Direction.UP);
        setState(GhostState.REGENERATING);
        Image img = sprites.get(Direction.UP);
        super.setSprite(img);
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level, Vector2 blinkyPos){
        double distanceToChange = 8.0 * level.getGameUnit();
        double actualDist = EnginesCaller.calculateDist(this.getPosition(),pacman.getPosition());
        if(actualDist < distanceToChange){
            return level.getSide(CharacterName.CLYDE);
        }else return pacman.getPosition();
    }

}
