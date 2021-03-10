package spellchecker;

import static sbcc.Core.*;

import java.util.*;

public class BasicDictionary implements Dictionary {
	BinaryTree tree = new BinaryTree();

	public ArrayList levelOrder(List<String> inOrder) {
		ArrayList words = new ArrayList();

		return words;
	}


	@Override
	public void importFile(String filename) throws Exception {
		List<String> words = new ArrayList();
		words = readFileAsLines(filename);
		// ATTEMPT IN FINDING SOME BALANCE
		tree.insert(words.get(words.size() / 2).trim());
		words.remove(words.size() / 2);

		Collections.shuffle(words);
		for (var word : words) {
			tree.insert(word.trim());
		}
	}


	@Override
	public void load(String filename) throws Exception {
		List<String> words = readFileAsLines(filename);
		for (var word : words) {
			tree.insert(word);
		}
	}


	@Override
	public void save(String filename) throws Exception {
		BinaryTreeNode cursor = tree.root;
		Stack<BinaryTreeNode> stack = new Stack();
		List<String> preOrder = new ArrayList();
		while (cursor != null) {
			// Write node to file
			preOrder.add(cursor.value);
			if (cursor.right != null)
				stack.push(cursor.right);
			if (cursor.left != null)
				cursor = cursor.left;
			else if (stack.size() > 0)
				cursor = stack.pop();
			else
				cursor = null;
		}
		writeFileAsLines(filename, preOrder);
	}


	@Override
	public String[] find(String word) {
		if (tree.search(word) || tree.root == null)
			return null;

		String[] options = { "", "" };
		BinaryTreeNode cur = tree.root;

		while (cur != null) {
			// WORD IS GREATER THAN VALUE
			if (word.compareToIgnoreCase(cur.value) > 0) {
				if (cur.right == null) {
					options[0] = cur.value;
					return options;
				} else {
					options[0] = cur.value;
					cur = cur.right;
				}
			}
			// WORD IS LESS THAN VALUE
			if (word.compareToIgnoreCase(cur.value) < 0) {
				if (cur.left == null) {
					options[1] = cur.value;
					return options;
				} else {
					options[1] = cur.value;
					cur = cur.left;
				}
			}
		}
		return null;
	}


	@Override
	public void add(String word) {
		tree.insert(word);
	}


	@Override
	public BinaryTreeNode getRoot() {
		return tree.root;
	}


	@Override
	public int getCount() {
		return tree.count;
	}

}
