import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int height;
    private int width;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;

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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        int i = 0;
        int N_COINS = 5;
        boolean f_continue;
        while (i<N_COINS) {
            f_continue = false;
            Coin myCoin = new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            for (Coin coin : coins) {
                if (coin.getPosition().equals(hero.getPosition()) || coin.getPosition().equals(myCoin.getPosition())) {
                    f_continue = true;
                    break;
                }
            }
            if (f_continue) continue;
            coins.add(myCoin);
            i++;
        }
        return coins;
    }

    public Arena() {
        this.height = 20;
        this.width = 40;
        this.hero = new Hero(10,10);
        this.walls = createWalls();
        this.coins = createCoins();
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
        retrieveCoins();
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
    public void retrieveCoins() {
        int remove = -1;
        for (int i=0; i<coins.size(); i++){
            if (hero.getPosition().equals(coins.get(i).getPosition())) remove = i;
        }
        if (remove != -1) coins.remove(remove);
    }
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000080"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        hero.draw(graphics);
    }

}