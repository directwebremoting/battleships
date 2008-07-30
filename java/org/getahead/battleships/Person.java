package org.getahead.battleships;

/**
 * A player of battleships
 */
public class Person {

    private String name;

    private Position position;

    private String color;

    private int score;

    public Person() {
	setName(BattleshipUtil.getRandomCaptainName());
	setColor(BattleshipUtil.getRandomLightHtmlColorString());
	setPosition(new Position());
	setScore(0);
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setPosition(Position position) {
	this.position = position;
    }

    public Position getPosition() {
	return position;
    }

    public void setColor(String color) {
	this.color = color;
    }

    public String getColor() {
	return color;
    }

    public void setScore(int score) {
	this.score = score;
    }

    public int getScore() {
	return score;
    }

    @Override
    public String toString() {
	return name;
    }
}
