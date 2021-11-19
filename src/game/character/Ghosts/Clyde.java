package game.character.Ghosts;

import game.Engines;
import game.character.PacMan;
import game.levels.Level;
import game.GameUtility.CharacterName;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Clyde extends Ghost{                       //le fantôme orange.
    public Clyde(Vector2 position) {
        super(7,GhostState.CHASING,position,new HashMap<>());
        this.sprites.put(Direction.UP,new ImageIcon("src/game/resources/Clyde/upClyde.png").getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon("src/game/resources/Clyde/downClyde.png").getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon("src/game/resources/Clyde/rightClyde.png").getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon("src/game/resources/Clyde/leftClyde.png").getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon("src/game/resources/Clyde/leftClyde.png").getImage());
        super.setSprite(this.sprites.get(Direction.LEFT));
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos){
        double distanceToChange = 8;
        if(Engines.calculateDist(this.getPosition(),pacman.getPosition()) < distanceToChange){
            return level.getSide(CharacterName.CLYDE);
        }else return pacman.getPosition();
    }

}
