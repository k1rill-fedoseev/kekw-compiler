package lexems;

import java.util.LinkedList;
import java.util.List;

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

    public ElementsList(List<IElement> list) {
        this.addAll(list);
    }

    @Override
    public String toString() {
        return "ElementsList" + super.toString();
    }

    public ElementsList clone() {
        return (ElementsList) super.clone();
    }
}
