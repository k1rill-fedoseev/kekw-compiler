package lexems;

import java.util.LinkedList;

public class ElementsList extends LinkedList<IElement> implements IElement {
    public ElementsList() {
        super();
    }

    public ElementsList(IElement elem) {
        this();
        add(elem);
    }

    public ElementsList(IElement elem1, IElement elem2) {
        this(elem1);
        add(elem2);
    }

    @Override
    public String toString() {
        return "ElementsList" + super.toString();
    }
}
