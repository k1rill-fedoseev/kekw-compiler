package lexems.builtin;

import exceptions.InterpreterException;
import lexems.IElement;

import java.util.List;

public interface IBuiltin {
    public IElement execute(List<IElement> argValues) throws InterpreterException;
}
