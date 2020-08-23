package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * A Stream reader that keeps track of the current parser.Position.
 */
public class PositionReader extends BufferedReader {

    private final Position position = new Position();
    // parser.Position before the latest call to "read", i.e. position
    // of the last character of the current token.
    private final Position previousPosition = new Position();

    public PositionReader(Reader reader) {
        super(reader);
    }

    public int read() throws IOException {
        previousPosition.set(position);
        int res = super.read();
        if (res > -1) {
            char c = (char) res;
            if (c == '\r' || c == '\n') {
                position.line += 1;
                position.column = 1;
            } else {
                position.column += 1;
            }
        }
        return res;
    }

    public Position getPosition() {
        return position;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }
}
