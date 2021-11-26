package utility;

import java.util.Objects;

public class Vector2 {
    public int x;
    public int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 multiply(int by){
        this.x *= by;
        this.y *= by;
        return this;
    }

    public static Vector2 add(Vector2 va, Vector2 vb){
        return new Vector2(va.x+vb.x, va.y+vb.y);
    }

    public void addToX(int i){
        this.x+= i;
    }
    public void addToY(int i){
        this.y+= i;
    }

    @Override
    public String toString() {
        return "Vector2{" +x +"," + y +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2 vector2 = (Vector2) o;
        return x == vector2.x && y == vector2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
