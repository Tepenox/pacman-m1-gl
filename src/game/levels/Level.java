package game.levels;

import game.utility.GhostName;
import game.utility.Vector2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Level {
    private final Vector2 blinkySide;     //top-right of maze
    private final Vector2 pinkySide;      //top-left of maze
    private final Vector2 inkySide;       //bottom-right of maze
    private final Vector2 clydeSide;      //bottom-left of maze

    private int[][] mazeArray;

    public Level(int level) throws IOException {
        fillMaze(level);
        this.blinkySide = new Vector2(mazeArray[0].length,mazeArray.length);
        this.pinkySide = new Vector2(0,mazeArray.length);
        this.inkySide = new Vector2(mazeArray[0].length,0);
        this.clydeSide = new Vector2(0,0);
    }

    private void fillMaze(int level) throws IOException {
        // Create Reader to read a file.
        BufferedReader br = new BufferedReader(new FileReader("src/game/resources/levels.text"));

        String line;
        boolean foundLevel = false;
        int mazeLine = 0;
        while((line = br.readLine())!= null) {
            if(line.startsWith("//"))
                continue;
            if(line.startsWith("Level")){
                line = line.substring(6);
                String[] lvlInfo = line.split("-");
                if(Integer.parseInt(lvlInfo[0]) == level){
                    this.mazeArray = new int[Integer.parseInt(lvlInfo[2])][Integer.parseInt(lvlInfo[1])];
                    foundLevel = true;
                }
                continue;
            }
            if(foundLevel && mazeLine < this.mazeArray.length){
                String[] parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].equals("-"))
                        parts[i] = "-1";
                    this.mazeArray[mazeLine][i] = Integer.parseInt(parts[i]);
                }
                mazeLine ++;
            }
        }
        br.close();
    }

    public int[][] getMazeArray() {
        return mazeArray;
    }

    public Vector2 getSide(GhostName ghostName){
        return switch (ghostName) {
            case BLINKY -> blinkySide;
            case PINKY -> pinkySide;
            case INKY -> inkySide;
            case CLYDE -> clydeSide;
        };
    }

}
