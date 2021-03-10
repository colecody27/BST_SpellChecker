package spellchecker;

import static sbcc.Core.*;

import java.util.*;
import java.util.regex.*;

public class BasicSpellChecker implements SpellChecker {
	BasicDictionary dict = new BasicDictionary();
	// LOAD DOC
	String document;
	List<String> words = new ArrayList();
	// SPELL CHECK
	Pattern pattern = Pattern.compile("\\b[\\w']+\\b");
	Matcher wordMatcher;
	int sIndex;

	@Override
	public void importDictionary(String filename) throws Exception {
		dict.importFile(filename);
	}


	@Override
	public void loadDictionary(String filename) throws Exception {
		dict.load(filename);
	}


	@Override
	public void saveDictionary(String filename) throws Exception {
		dict.save(filename);
	}


	@Override
	public void loadDocument(String filename) throws Exception {
		document = readFile(filename);
		wordMatcher = pattern.matcher(document);
		words = readFileAsLines(filename);
	}


	@Override
	public void saveDocument(String filename) throws Exception {
		writeFile(filename, document);
	}


	@Override
	public String getText() {
		return document;
	}


	@Override
	public String[] spellCheck(boolean continueFromPrevious) {
		String[] unknown = new String[4];
		if (continueFromPrevious == false)
			sIndex = 0;

		// ENTER LOOP
		while (wordMatcher.find(sIndex)) {
			String word = document.substring(wordMatcher.start(), wordMatcher.end());
			sIndex = wordMatcher.end();
			if (dict.find(word) == null)
				continue;
			else {
				unknown[0] = word;
				unknown[1] = Integer.toString(wordMatcher.start());
				unknown[2] = dict.find(word)[0];
				unknown[3] = dict.find(word)[1];
				return unknown;
			}
		}
		return null;
	}


	@Override
	public void addWordToDictionary(String word) {
		dict.add(word);
	}


	@Override
	public void replaceText(int startIndex, int endIndex, String replacementText) {
		StringBuilder sb = new StringBuilder(document);
		document = sb.replace(startIndex, endIndex, replacementText).toString();
		wordMatcher = pattern.matcher(document);
		sIndex = endIndex;
	}

}
