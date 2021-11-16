package game.levels;

import game.GameUtility.CharacterName;
import utility.Vector2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Level {
    private final Vector2 blinkySide;     //top-right of maze
    private final Vector2 pinkySide;      //top-left of maze
    private final Vector2 inkySide;       //bottom-right of maze
    private final Vector2 clydeSide;      //bottom-left of maze

    private Vector2 blinkySpawn = null;
    private Vector2 pinkySpawn = null;
    private Vector2 inkySpawn = null;
    private Vector2 clydeSpawn = null;
    private Vector2 pacManSpawn = null;

    private int pacGommeCount = 0;
    private int[][] mazeArray;

    public Level(int level){
        try {fillMaze(level);} catch (IOException e) {
            System.out.println("fileNotFound");
        }
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
                    initiateVar(parts[i],i,mazeLine);
                    if(parts[i].equals("2") ){
                        pacGommeCount++;
                    }
                }
                mazeLine ++;
            }
        }
        br.close();
    }

    private void initiateVar(String value,int x,int y){
        switch (value) {
            case "2" -> pacGommeCount++;
            case "5" -> pacManSpawn = new Vector2(x, y);
            case "6" -> blinkySpawn = new Vector2(x, y);
            case "7" -> clydeSpawn = new Vector2(x, y);
            case "8" -> inkySpawn = new Vector2(x, y);
            case "9" -> pinkySpawn = new Vector2(x, y);
        }

    }

    public int[][] getLevelArray() {
        return mazeArray;
    }

    public Vector2 getSide(CharacterName ghostName){
        return switch (ghostName) {
            case BLINKY -> blinkySide;
            case PINKY -> pinkySide;
            case INKY -> inkySide;
            case CLYDE -> clydeSide;
            default -> null;
        };
    }

    public Vector2 getSpawn(CharacterName name){
        return switch (name) {
            case BLINKY -> blinkySpawn;
            case PINKY -> pinkySpawn;
            case INKY -> inkySpawn;
            case CLYDE -> clydeSpawn;
            case PACMAN -> pacManSpawn;
        };
    }

    public int getPacGommeCount(){
        return pacGommeCount;
    }

    public void removePacGomme(int x, int y, int value){
        this.pacGommeCount--;
        mazeArray[y][x] = value;

    }


}
