public class Hero {
    private Position position;
    public Hero(int x, int y) {
        this.position = new Position(x,y);
    }
    public Position getPosition() {
        return position;
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY()-1);
    }
    public Position moveDown() {
        return new Position(position.getX(), position.getY()+1);
    }
    public Position moveRight() {
        return new Position(position.getX()+1, position.getY());
    }
    public Position moveLeft() {
        return new Position(position.getX()-1, position.getY());
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}