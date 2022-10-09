import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen = null;
    private int screenCols = 40;
    private int screenRows = 20;
    private Hero hero = new Hero(10,10);

    public Game() throws IOException {
        try {
            TerminalSize terminalSize = new TerminalSize(screenCols, screenRows);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException {
        while (true){
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
            if (key.getKeyType() == KeyType.EOF) break;
            processKey(key);
        }
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    private void processKey(com.googlecode.lanterna.input.KeyStroke key) {
        switch(key.getKeyType()){
            case ArrowUp: {
                hero.moveUp();
                break;
            }
            case ArrowDown: {
                hero.moveDown();
                break;
            }
            case ArrowRight: {
                hero.moveRight();
                break;
            }
            case ArrowLeft: {
                hero.moveLeft();
                break;
            }
        }
    }
}