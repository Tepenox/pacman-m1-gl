package game.GameEngine;

import game.character.Ghosts.Ghost;
import game.character.Ghosts.GhostState;

import java.util.TimerTask;

class AutoChangeGhostsState extends TimerTask {
    public void run() {
        PacManGame.numberOfPhaseLeft--;
        if(PacManGame.ghostPhase == GhostState.CHASING) {
            PacManGame.ghostPhase = GhostState.DISPERSION;
            if(PacManGame.numberOfPhaseLeft>0)
                createPhaseTimer(6000);
        }else {
            PacManGame.ghostPhase = GhostState.CHASING;
            if(PacManGame.numberOfPhaseLeft>0)
                createPhaseTimer(20000);
            else
                PacManGame.ghostPhaseTimer.cancel();
        }

        for (Ghost ghost:PacManGame.ghosts) {
            if(ghost.state == GhostState.CHASING || ghost.state == GhostState.DISPERSION){
                ghost.state = PacManGame.ghostPhase;
            }
        }
    }

    public static void createPhaseTimer(int timeToWait){
        PacManGame.ghostPhaseTimer = new java.util.Timer();
        PacManGame.ghostPhaseTimer.schedule(new AutoChangeGhostsState(), timeToWait);
    }
}
