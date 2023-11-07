import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testWordPalindromeA() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("A"));
        assertFalse(palindrome.isPalindrome("aA"));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("rancor"));
        assertFalse(palindrome.isPalindrome("aaaaab"));
    }

    @Test
    public void testOffByOnePalindrome() {
        var cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome(" ", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("A", cc));
        assertFalse(palindrome.isPalindrome("Aa", cc));
        assertFalse(palindrome.isPalindrome("abcdefghjklmnopq", cc));
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertFalse(palindrome.isPalindrome("noon", cc));
        assertTrue(palindrome.isPalindrome("bababa", cc));
        assertTrue(palindrome.isPalindrome("bdefcc", cc));
        assertTrue(palindrome.isPalindrome("%a&", cc));
        assertTrue(palindrome.isPalindrome("%&", cc));
        assertFalse(palindrome.isPalindrome("*&", cc));
    }
}
