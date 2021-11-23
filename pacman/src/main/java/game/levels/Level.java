package game.levels;

import game.GameUtility.CharacterName;
import utility.Vector2;

import java.io.*;

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
    private Vector2 fruitSpawn = null;

    private int pacGommeCount = 0;
    private int[][] mazeArray;
    private int gameUnit;
    private int mazeWidth;
    private int mazeHeight;
    private int levelNumber;

    public Level(int level, int gameUnit){
        this.levelNumber = level;
        this.gameUnit = gameUnit;
        try {fillMaze(level);} catch (IOException e) {
            System.out.println("fileNotFound");
        }
        this.blinkySide = new Vector2(mazeArray[0].length-1,0).multiply(gameUnit);
        this.pinkySide = new Vector2(0,0).multiply(gameUnit);
        this.inkySide = new Vector2(mazeArray[0].length-1,mazeArray.length-1).multiply(gameUnit);
        this.clydeSide = new Vector2(0,mazeArray.length-1).multiply(gameUnit);

    }

    private void fillMaze(int level) throws IOException {
        // Create Reader to read a file.
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("levels.text")));

        String line;
        boolean foundLevel = false;
        int mazeLine = 0;
        while((line = br.readLine())!= null) {
            if(line.startsWith("//"))
                continue;
            if(line.startsWith("Level ")){
                line = line.substring(6);
                String[] lvlInfo = line.split("-");
                if(Integer.parseInt(lvlInfo[0]) == level){
                    this.mazeWidth = Integer.parseInt(lvlInfo[1]);
                    this.mazeHeight = Integer.parseInt(lvlInfo[2]);
                    this.mazeArray = new int[mazeHeight][mazeWidth];
                    foundLevel = true;
                }
                continue;
            }
            if(foundLevel && mazeLine < this.mazeArray.length){
                String[] parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].equals("-"))
                        parts[i] = "-1";
                    this.mazeArray[mazeLine][i] = initiateVar(parts[i],i,mazeLine);
                }
                mazeLine ++;
            }
        }
        br.close();
    }

    private int initiateVar(String value,int x,int y){
        switch (value) {
            case "0" -> {
                return 0;
            }
            case "1" -> {
                return 1;
            }
            case "2" -> {
                pacGommeCount++;
                return 2;
            }
            case "3" -> {
                pacGommeCount++;
                return 3;
            }
            case "4" -> {
                fruitSpawn = new Vector2(x, y).multiply(gameUnit);
                return -1;
            }
            case "5" -> {
                pacManSpawn = new Vector2(x, y).multiply(gameUnit);
                pacGommeCount++;
                return 2;
            }
            case "6" -> {
                blinkySpawn = new Vector2(x, y).multiply(gameUnit);
                return 6;
            }
            case "7" -> {
                clydeSpawn = new Vector2(x, y).multiply(gameUnit);
                return 7;
            }
            case "8" -> {
                inkySpawn = new Vector2(x, y).multiply(gameUnit);
                return 8;
            }
            case "9" -> {
                pinkySpawn = new Vector2(x, y).multiply(gameUnit);
                return 9;
            }
        }
        return -1;
    }

    public int[][] getLevelArray() {
        return mazeArray;
    }

    public int getGameUnit() {
        return gameUnit;
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

    public Vector2 getFruitSpawn() {
        return fruitSpawn;
    }

    public Vector2 getSpawn(CharacterName name){
        return switch (name) {
            case BLINKY -> Vector2.add(blinkySpawn, new Vector2(gameUnit/2,0));
            case PINKY -> Vector2.add(pinkySpawn, new Vector2(gameUnit/2,0));
            case INKY -> Vector2.add(inkySpawn, new Vector2(gameUnit/2,0));
            case CLYDE -> Vector2.add(clydeSpawn, new Vector2(gameUnit/2,0));
            case PACMAN -> Vector2.add(pacManSpawn, new Vector2(gameUnit/2,0));
        };
    }

    public int getPacGommeCount(){
        return pacGommeCount;
    }

    public void removePacGomme(int x, int y, int value){
        this.pacGommeCount--;
        mazeArray[y][x] = value;

    }

    public int getMazeWidth() {
        return mazeWidth;
    }

    public int getMazeHeight() {
        return mazeHeight;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
