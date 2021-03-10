package spellchecker;

public class BinaryTree {
	BinaryTreeNode root;
	int count = 0;

	public void insert(String text) {
		if (search(text) == false) {
			boolean found = false;
			if (root == null) {
				root = new BinaryTreeNode(text);
				found = true;
				count++;
			}
			BinaryTreeNode cursor = root;
			while (found != true) {
				if (text.compareToIgnoreCase(cursor.value) > 0) {
					if (cursor.right == null) {
						cursor.right = new BinaryTreeNode(text);
						found = true;
					} else
						cursor = cursor.right;
				} else if (text.compareToIgnoreCase(cursor.value) < 0) {
					if (cursor.left == null) {
						cursor.left = new BinaryTreeNode(text);
						found = true;
					} else
						cursor = cursor.left;
				}
			}
			/*
			 * else if (text.compareToIgnoreCase(cursor.value) > 0) {// Go right if
			 * (cursor.right == null) { cursor.right = new BinaryTreeNode(text); count++;
			 * cursor = root; } else { cursor = cursor.right; insert(text); }
			 * 
			 * } else if (text.compareToIgnoreCase(cursor.value) < 0) {// Go left if
			 * (cursor.left == null) { cursor.left = new BinaryTreeNode(text); count++;
			 * cursor = root; } else { cursor = cursor.left; insert(text); } }
			 */
		}
	}


	public boolean search(String text) {
		if (root == null)
			return false;
		BinaryTreeNode cursor = root;
		while (cursor != null) {
			if (text.compareToIgnoreCase(cursor.value) == 0) {
				return true;
			}
			if (text.compareToIgnoreCase(cursor.value) > 0) // Go right
				cursor = cursor.right;
			else
				cursor = cursor.left; // Go left
		}
		return false;
	}
}
