package game.GameObject.character.Ghosts;

public enum GhostState {
    FRIGHTENED,TWINKLING,                   //eatable and don't kill Pacman
    CHASING,DISPERSION,                     //no eatable and kill PacMan
    EATEN,REGENERATING                      //no eatable and don't kill Pacman
}

