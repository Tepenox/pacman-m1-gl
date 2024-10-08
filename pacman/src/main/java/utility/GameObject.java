package utility;

import java.awt.*;

public class GameObject {
    public int id;
    protected Vector2 position;
    private Image sprite;

    public GameObject(int id, Vector2 position) {
        this.id = id;
        this.position = position;
    }

    public Image getSprite() {
        return sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }
}
