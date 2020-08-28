package lexems.builtin;

import lexems.IElement;

import java.util.List;

public interface IBuiltin {
    public IElement execute(List<IElement> argValues);
}
