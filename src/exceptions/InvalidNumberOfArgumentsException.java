package exceptions;

public class InvalidNumberOfArgumentsException extends InterpreterException {
    public InvalidNumberOfArgumentsException(int got, int expected) {
        super("Got " + got + ", expected " + expected + " arguments");
    }
}
