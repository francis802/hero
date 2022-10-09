import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int height = 20;
    private int width = 40;
    private Hero hero = new Hero(10,10);

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
        hero.draw(graphics);
    }
}