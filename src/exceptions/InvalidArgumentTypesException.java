package exceptions;

public class InvalidArgumentTypesException extends InterpreterException {
    public InvalidArgumentTypesException() {
        super("Got invalid argument types");
    }
}
