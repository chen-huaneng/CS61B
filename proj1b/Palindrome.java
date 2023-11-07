import java.util.Objects;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        var deque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); ++i) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        String reverseOrder = "";
        Deque<Character> deque = wordToDeque(word);
        for (int i = 0; i < word.length(); ++i) {
            reverseOrder += deque.removeLast();
        }
        return Objects.equals(word, reverseOrder);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        for (int i = 0; i < word.length() / 2; ++i) {
            if (!cc.equalChars(deque.removeLast(), word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
