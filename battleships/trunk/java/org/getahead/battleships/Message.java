package org.getahead.battleships;

/**
 * Something someone typed
 */
public class Message {

    private long id = System.currentTimeMillis();

    private String text;

    private Person author;

    public Message(String text, Person author) {
	this.setText(text);
	this.setAuthor(author);
    }

    public void setId(long id) {
	this.id = id;
    }

    public long getId() {
	return id;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String getText() {
	return text;
    }

    public void setAuthor(Person author) {
	this.author = author;
    }

    public Person getAuthor() {
	return author;
    }
}
