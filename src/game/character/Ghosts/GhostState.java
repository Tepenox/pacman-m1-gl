package game.character.Ghosts;

public enum GhostState {
    FRIGHTENED,                             //eatable
    CHASING,DISPERSION,                     //no eatable and kill PacMan
    EATEN,REGENERATING                      //no eatable and don't kill Pacman
}

