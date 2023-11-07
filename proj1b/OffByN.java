public class OffByN implements CharacterComparator {
    private final int offBy;
    public OffByN(int N) {
        offBy = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == offBy;
    }
}
