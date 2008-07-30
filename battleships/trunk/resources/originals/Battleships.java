package org.getahead.battleships;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.ScriptProxy;
import org.directwebremoting.proxy.dwr.Util;

@SuppressWarnings("unchecked")
public class Battleships {

    public Person initPerson() {
        WebContext webContext = WebContextFactory.get();
        ScriptSession scriptSession = webContext.getScriptSession();
        Person person = new Person();
        scriptSession.setAttribute("person", person);
        return person;
    }

    public void addMessage(String text) {
        Person person = getPerson();
        addMessageToList(text, person);
        refreshEveryone();
    }

    private Person getPerson() {
        WebContext webContext = WebContextFactory.get();
        ScriptSession scriptSession = webContext.getScriptSession();
        Person person = (Person) scriptSession.getAttribute("person");

        if (person == null) {
            throw new IllegalArgumentException("Missing person");
        }
        return person;
    }

    private void addMessageToList(String text, Person person) {
        if (text != null && text.trim().length() > 0) {
            messages.addFirst(new Message(text, person));
            while (messages.size() > 20) {
                messages.removeLast();
            }
        }
    }

    protected LinkedList<Message> messages = new LinkedList<Message>();

    private void refreshEveryone() {
        WebContext wctx = WebContextFactory.get();
        String currentPage = wctx.getCurrentPage();
        Collection<ScriptSession> sessions = wctx.getScriptSessionsByPage(currentPage);

        ScriptProxy all = new ScriptProxy(sessions);
        all.addFunctionCall("serverUpdate", messages, getEveryone(sessions));
    }

    /** Some player is shooting */
    public void shoot(Position position) {
        Person me = getPerson();
        addMessageToList(me + " shoots at " + position, me);
        WebContext wctx = WebContextFactory.get();
        String currentPage = wctx.getCurrentPage();
        Collection<ScriptSession> othersSessions = wctx.getScriptSessionsByPage(currentPage);
        List<Person> players = getEveryone(othersSessions);
        for (Person him : players) {
            if (position.equals(him.getPosition())) {
                if (me == him) {
                    me.setScore(me.getScore() - 1);
                    addMessageToList(me + " fragged himself!", system);
                } else {
                    me.setScore(me.getScore() + 1);
                    him.setScore(him.getScore() - 1);
                    addMessageToList(me + " fragged " + him, system);
                }
            }
        }
        refreshEveryone();
    }

    /** Get a list of all the players */
    private List<Person> getEveryone(Collection<ScriptSession> sessions) {
        List<Person> players = new ArrayList<Person>();
        for (ScriptSession session : sessions) {
            Person person = (Person) session.getAttribute("person");
            if (person != null) {
                players.add(person);
            }
        }
        return players;
    }

    Person system = new Person(); {
        system.setName("System");
        system.setColor("#eee");
    }}




















































/*
package org.getahead.battleships;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.ScriptProxy;

@SuppressWarnings("unchecked")
public class Battleships {
    public Person initPerson() {
        WebContext webContext = WebContextFactory.get();
        ScriptSession scriptSession = webContext.getScriptSession();
        Person person = new Person();
        scriptSession.setAttribute("person", person);
        return person;
    }

    public void addMessage(String text) {
        Person person = getPerson();
        addMessageToList(text, person);
        refreshEveryone2();
    }

    private void refreshEveryone() {
        WebContext wctx = WebContextFactory.get();
        String currentPage = wctx.getCurrentPage();
        Collection<ScriptSession> sessions = wctx.getScriptSessionsByPage(currentPage);
        List<Person> players = getEveryone(sessions);

        ScriptProxy all = new ScriptProxy(sessions);
        all.addFunctionCall("serverUpdate", messages, players);
    }

    private void refreshEveryone2() {
        WebContext wctx = WebContextFactory.get();
        String currentPage = wctx.getCurrentPage();
        Collection<ScriptSession> sessions = wctx.getScriptSessionsByPage(currentPage);
        List<Person> players = getEveryone(sessions);

        Util util = new Util(sessions);
        util.removeAllOptions("chatlog");
        util.addOptions("chatlog", formatMessages());
        util.removeAllRows("scores");
        util.addRows("scores", formatScores(players), "{escapeHtml:false}");
    }

    private String[][] formatScores(List<Person> players) {
        String[][] reply = new String[players.size()][];
        int row = 0;
        for (Person person : players) {
            reply[row] = new String[] {
                "<span style='color:" + person.getColor() + ";'>" + person.getName() + "</span>&nbsp;",
                "<span style='color:" + person.getColor() + ";'>" + person.getScore() + "</span>",
            };
            row++;
        }
        return reply;
    }

    // Convert a List<Message> to a String[]
    private String[] formatMessages() {
        String[] reply = new String[messages.size()];
        int row = 0;
        for (Message message : messages) {
            reply[row] = "<span class='chatname'>" + message.getAuthor().getName() + ": </span>" +
            "<span class='chattext' style='color:" + message.getAuthor().getColor() + ";'>" +
            message.getText() + "</span>";
            row++;
        }
        return reply;
    }

    // Extract the players data from the ScriptSession
    private Person getPerson() {
        WebContext webContext = WebContextFactory.get();
        ScriptSession scriptSession = webContext.getScriptSession();
        Person person = (Person) scriptSession.getAttribute("person");

        if (person == null) {
            throw new IllegalArgumentException("Missing person");
        }
        return person;
    }

    // Internal function to add a message to the list, and trim list length to 20 messages
    private void addMessageToList(String text, Person person) {
        if (text != null && text.trim().length() > 0) {
            messages.addFirst(new Message(text, person));
            while (messages.size() > 20) {
                messages.removeLast();
            }
        }
    }

    protected LinkedList<Message> messages = new LinkedList<Message>();

    // Some player is shooting
    public void shoot(Position position) {
        Person me = getPerson();
        addMessageToList(me + " shoots at " + position, me);
        WebContext wctx = WebContextFactory.get();
        String currentPage = wctx.getCurrentPage();
        Collection<ScriptSession> othersSessions = wctx.getScriptSessionsByPage(currentPage);
        List<Person> players = getEveryone(othersSessions);
        for (Person him : players) {
            if (position.equals(him.getPosition())) {
                if (me == him) {
                    me.setScore(me.getScore() - 1);
                    addMessageToList(me + " fragged himself!", system);
                } else {
                    me.setScore(me.getScore() + 1);
                    him.setScore(him.getScore() - 1);
                    addMessageToList(me + " fragged " + him, system);
                }
            }
        }
        refreshEveryone2();
    }

    // Get a list of all the players
    private List<Person> getEveryone(Collection<ScriptSession> sessions) {
        List<Person> players = new ArrayList<Person>();
        for (ScriptSession session : sessions) {
            Person person = (Person) session.getAttribute("person");
            if (person != null) {
                players.add(person);
            }
        }
        return players;
    }

    Person system = new Person(); {
        system.setName("System");
        system.setColor("#eee");
    }
}
*/
