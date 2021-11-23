package game.GameEngine;

import game.character.Ghosts.Ghost;
import game.character.Ghosts.GhostState;

import java.util.TimerTask;

class AutoChangeGhostsState extends TimerTask {
    public void run() {
        GameLogic.numberOfPhaseLeft--;
        if(GameLogic.ghostPhase == GhostState.CHASING) {
            GameLogic.ghostPhase = GhostState.DISPERSION;
            if(GameLogic.numberOfPhaseLeft>0)
                createPhaseTimer(6000);
        }else {
            GameLogic.ghostPhase = GhostState.CHASING;
            if(GameLogic.numberOfPhaseLeft>0)
                createPhaseTimer(20000);
            else
                GameLogic.ghostPhaseTimer.cancel();
        }

        for (Ghost ghost: GameLogic.ghosts) {
            if(ghost.state == GhostState.CHASING || ghost.state == GhostState.DISPERSION){
                ghost.state = GameLogic.ghostPhase;
            }
        }
    }

    public static void createPhaseTimer(int timeToWait){
        GameLogic.ghostPhaseTimer = new java.util.Timer();
        GameLogic.ghostPhaseTimer.schedule(new AutoChangeGhostsState(), timeToWait);
    }
}
