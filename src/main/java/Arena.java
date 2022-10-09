import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int height;
    private int width;
    private Hero hero;
    private List<Wall> walls;

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    public Arena() {
        this.height = 20;
        this.width = 40;
        this.hero = new Hero(10,10);
        this.walls = createWalls();
    }
    public void processKey(KeyStroke key){
        switch(key.getKeyType()){
            case ArrowUp: {
                moveHero(hero.moveUp());
                break;
            }
            case ArrowDown: {
                moveHero(hero.moveDown());
                break;
            }
            case ArrowRight: {
                moveHero(hero.moveRight());
                break;
            }
            case ArrowLeft: {
                moveHero(hero.moveLeft());
                break;
            }
        }
    }
    private void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position){
        for (Wall wall:walls)
            if (wall.getPosition().equals(position)) return false;

        if (position.getX()<width && position.getX()>=0 && position.getY()<height && position.getY()>=0)
            return true;
        return false;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        hero.draw(graphics);
    }
}