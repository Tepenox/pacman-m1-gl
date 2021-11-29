package game;

import game.GameEngine.GameLogic;
import game.character.Ghosts.Ghost;
import game.character.Ghosts.GhostState;

import java.util.TimerTask;
import java.util.Timer;

public class AutoChangeGhostsState{

    private static int numberOfPhaseLeft;
    private static java.util.Timer ghostPhaseTimer;
    private static long lastTime;
    private static final long chaseTime = 20000;
    private static final long dispersionTime = 5000;

    public static void start() {
        numberOfPhaseLeft = 7;
        lastTime = System.currentTimeMillis();
        createPhaseTimer();
    }

    private static void createPhaseTimer(){
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                if(numberOfPhaseLeft <= 0){
                    GameLogic.ghostPhase = GhostState.CHASING;
                    stop();
                }
                long now = System.currentTimeMillis();
                if(GameLogic.ghostPhase == GhostState.CHASING && now-lastTime >= chaseTime){
                    numberOfPhaseLeft--;
                    GameLogic.ghostPhase = GhostState.DISPERSION;
                    lastTime = now;
                    changeGhostState();
                    return;
                }
                if(GameLogic.ghostPhase == GhostState.DISPERSION && now-lastTime >= dispersionTime){
                    numberOfPhaseLeft--;
                    GameLogic.ghostPhase = GhostState.CHASING;
                    lastTime = now;
                    changeGhostState();
                    return;
                }
            }
        };
        ghostPhaseTimer = new Timer();
        ghostPhaseTimer.schedule(timerTask,0,1000);
    }

    public static void stop() {
        if(ghostPhaseTimer != null){
            ghostPhaseTimer.cancel();
            ghostPhaseTimer.purge();
        }
    }

    private static void changeGhostState() {
        for (Ghost ghost: GameLogic.ghosts) {
            if(ghost.state == GhostState.CHASING || ghost.state == GhostState.DISPERSION){
                ghost.state = GameLogic.ghostPhase;
            }
        }

    }
}
