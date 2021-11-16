package utility;

import java.awt.*;

public class GameObject {
    public int id;
    protected Vector2 position;
    private Image sprite;

    public GameObject(int id, Vector2 position, Image img) {
        this.id = id;
        this.position = position;
        this.sprite = img;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }
}
