package game.object;

import game.GameEngine.Engines;
import game.GameEngine.GameLogic;
import game.character.Ghosts.Ghost;
import game.character.Ghosts.GhostState;
import utility.Direction;
import utility.Vector2;
import java.util.*;

public class GhostBase {
    private final Vector2 baseEntry;
    private final int YUp;
    private final int YDown;

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

    public Vector2 getBaseEntry() {
        return baseEntry;
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
                removeGhostFromBase();
            }
        }
    }

    private void removeGhostFromBase(){
        ghostExiting.setState(GameLogic.ghostPhase);
        ghostExiting.setDirection(Direction.LEFT);
        ghostExiting = null;
        currentRegenTime = 0;
        if(ghosts.size() == 0)
            isRegenerating = false;
    }

    public void addAllGhostToBase(List<Ghost> ghostList){
        for (Ghost ghost:ghostList) {
            addGhostToBase(ghost);
        }
    }

    public void addGhostToBase(Ghost ghost){
        ghosts.add(ghost);
        ghost.setState(GhostState.REGENERATING);
        if(!isRegenerating)
            isRegenerating = true;
        ghost.setDirection(Direction.DOWN);
    }

    public void addGhostApproxPos(Ghost ghost){
        Engines.move(ghost, baseEntry.x, baseEntry.y);
        addGhostToBase(ghost);
    }

    private void checkRegeneratedGhost() {
        if(isRegenerating && ghostExiting == null && currentRegenTime > regenTime && ghosts.size() >= 1 ){
            ghostExiting = ghosts.remove(0);
        }
    }
}
