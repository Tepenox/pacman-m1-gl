package game;

import engines.CoreKernel;
import game.utility.GameState;
import game.utility.Vector2;

import javax.swing.*;

public class GameEngine{
    private static final JFrame frame = CoreKernel.createFrame(380, 420, "Pacman");
    private GameState gameState = GameState.PAUSED;


    public static void main(String[] args) {

    }
}
