/*
 * Copyright 2005 Joe Walker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.getahead.battleships;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import org.directwebremoting.util.LocalUtil;

/**
 * @author Joe Walker [joe at getahead dot ltd dot uk]
 */
public class BattleshipUtil {

    /**
     * @return A new random color that contrasts with black
     */
    public static String getRandomLightHtmlColorString() {
	int red = random.nextInt(10) + 6;
	int green = random.nextInt(10) + 6;
	int blue = random.nextInt(10) + 6;

	return "#" + COLOR_LOOKUP[red] + COLOR_LOOKUP[green]
		+ COLOR_LOOKUP[blue];
    }

    /**
     * @return A random captains name
     */
    public static String getRandomCaptainName() {
	String firstname = FIRSTNAMES[random.nextInt(FIRSTNAMES.length)];
	String surname = SURNAMES[random.nextInt(SURNAMES.length)];
	String name = LocalUtil.replace(surname, "%", firstname);
	return name;
    }

    private static Random random = new Random();

    private static final char[] COLOR_LOOKUP = { '0', '1', '2', '3', '4', '5',
	    '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private static final String[] FIRSTNAMES = { "Brian", "Percy", "Ming",
	    "Dave", "Shane", "Mark", "Bob", "Atilla", "Gary", "Jack", "Ethan",
	    "Jamie", "Alfie", "Max", "Reece", "Sean", "Leon", "Declan", "Ross",
	    "Lionel", "Louis", "Leroy", "Bruce", "Brendon" };

    private static final String[] SURNAMES = { "% the Merciless",
	    "Bloodsucker %", "% the Fearless", "% the Hunn", "Grim %",
	    "Cutthroat %", "Hatchet %", "% the Pitiless", "% the Ruthless",
	    "Savage %", "Barbarian %", "Brutal %", "Wolfman %", "% the Fox",
	    "Slasher %", "% the Cannibal", "Terminator %", "% Ironfist",
	    "Vindictive %", "Iceman %", "Terrible %", "Pirate %" };
}
