package pvpoverhaulmain;

import java.util.Random;

public class words {

	static Random r = new Random();
	final static int NO_SENTS = 20;
    final static String SPACE = " ";
    final static String PERIOD = ".";
	
	public static Random rand = new Random();
	public static String article[] = { "the", "a", "one", "some", "any" };
	public static String noun[] = { "I", "eye", "mouth", "teeth", "soul", "you", "it", "me", "boy", "girl", "dog", "town", "car", "area","book","business","case","child","company","country","day","eye","fact","family","government","group","hand","home","job","life","lot","man","money","month","mother","Mr","night","number","part","people","place","point","problem","program","question","right","room","school","state","story","student","study","system","thing","time","water","way","week","woman","word","work","world","year" };
	public static String verb[] = { "pierced", "stared", "drove", "jumped", "ran", "walked", "skipped", "cried", "burned", "wailed", "screamed", "lied", "watched", "slithered", "staggered", "leaped", "ran", "laughed" };
	public static String preposition[] = { "to", "from", "over", "under", "on", "of", "with", "at", "into", "during", "including", "until", "among", "despite", "towards", "upon", "concerning", "in", "for", "like", "about", "since", "without", "behind", "within", "without", "with", "along", "following", "across", "behind", "beyond", "around", "down", "off", "near" };
	public static String connection[] = { "and, ", ", yet ", "but, ", "or, ", "also, ", " although, " };
	
	public static String getRandomNoun() {
		return noun[rand.nextInt(noun.length)];
	}
	
	public static String getRandomArticle() {
		return article[rand.nextInt(article.length)];
	}
	
	public static String getRandomVerb() {
		return verb[rand.nextInt(verb.length)];
	}
	
	public static String getRandomPrep() {
		return preposition[rand.nextInt(preposition.length)];
	}
	
	public static String getRandomConnection() {
		return connection[rand.nextInt(connection.length)];
	}
	
	public static String generateSentence() {
		 String sentence;
		 sentence = words.getRandomArticle();
		 char c = sentence.charAt(0);
		 sentence = sentence.replace( c, Character.toUpperCase(c) );
		 sentence += SPACE + words.getRandomNoun() + SPACE;
		 sentence += (words.getRandomVerb() + SPACE + words.getRandomPrep());
		 sentence += (SPACE + words.getRandomArticle() + SPACE + words.getRandomNoun());
		 if(r.nextBoolean()==true) {
		      sentence += (SPACE + words.getRandomConnection());
		      sentence += words.getRandomArticle();
		 	  sentence += SPACE + words.getRandomNoun() + SPACE;
		 	  sentence += (words.getRandomVerb() + SPACE + words.getRandomPrep());
		 	  sentence += (SPACE + words.getRandomArticle() + SPACE + words.getRandomNoun());
		}	
		sentence += PERIOD;
		return sentence;
	}
	
}
