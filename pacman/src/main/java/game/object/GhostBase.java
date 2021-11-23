package game.object;

import game.GameEngine.Engines;
import game.GameEngine.GameLogic;
import game.GameUtility.CharacterName;
import game.character.Ghosts.Ghost;
import utility.Direction;
import utility.Vector2;

import java.sql.Time;
import java.util.*;

public class GhostBase {
    private Vector2 baseEntry;
    public final int YUp;
    public final int YDown;

    private List<Ghost> ghosts = new ArrayList<>();
    private Ghost ghostExiting;
    private static final int regenTime = 3;
    private int currentRegenTime = 0;
    private boolean isRegenerating = false;

    public GhostBase(Vector2 baseEntry) {
        this.baseEntry = baseEntry;
        this.YUp = baseEntry.y + 2 * GameLogic.gameUnit;
        this.YDown = baseEntry.y + 4 * GameLogic.gameUnit;
    }

    public void startRegenTimer(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            public void run(){
                if(isRegenerating)
                    currentRegenTime++;
                else currentRegenTime = 0;
            }
        }, 0, 1000);
    }

    public void regenerateGhost(){
        checkRegeneratedGhost();
        for (Ghost ghost:ghosts) {
            if(ghost.getDirection() == Direction.DOWN){
                if(ghost.getPosition().y >= YDown) {
                    ghost.setDirection(Direction.UP);
                    Engines.moveGameObjectByOneStep(ghost, Direction.UP, 3);
                }
                Engines.moveGameObjectByOneStep(ghost, Direction.DOWN, 3);
            }
            if(ghost.getDirection() == Direction.UP){
                if(ghost.getPosition().y <= YUp) {
                    ghost.setDirection(Direction.DOWN);
                    Engines.moveGameObjectByOneStep(ghost, Direction.DOWN, 3);
                }
                Engines.moveGameObjectByOneStep(ghost, Direction.UP, 3);
            }
        }
        if(ghostExiting != null){
            if(ghostExiting.getPosition().x > baseEntry.x){
                ghostExiting.setDirection(Direction.LEFT);
                Engines.moveGameObjectByOneStep(ghostExiting, Direction.LEFT, 3);
                return;
            }
            if(ghostExiting.getPosition().x < baseEntry.x){
                ghostExiting.setDirection(Direction.RIGHT);
                Engines.moveGameObjectByOneStep(ghostExiting, Direction.RIGHT, 3);
                return;
            }
            if(ghostExiting.getPosition().y > baseEntry.y){
                ghostExiting.setDirection(Direction.UP);
                Engines.moveGameObjectByOneStep(ghostExiting, Direction.UP, 3);
                return;
            }
            if(ghostExiting.getPosition().y <= baseEntry.y){
                ghostExiting.setState(GameLogic.ghostPhase);
                ghostExiting.setDirection(Direction.LEFT);
                ghostExiting = null;
            }
        }
    }

    public void addAllGhostToBase(List<Ghost> ghostList){
        for (Ghost ghost:ghostList) {
            addGhostToBase(ghost);
        }
    }

    public void addGhostToBase(Ghost ghost){
        ghosts.add(ghost);
        if(!isRegenerating)
            isRegenerating = true;
    }

    public void addGhostApproxPos(Ghost ghost){
        Engines.move(ghost, baseEntry.x, baseEntry.y);
        addGhostToBase(ghost);
    }

    private void checkRegeneratedGhost() {
        if(isRegenerating && currentRegenTime > regenTime && ghosts.size() >= 1){
            ghostExiting = ghosts.remove(0);
            currentRegenTime = 0;
        }
    }
}
