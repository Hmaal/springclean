package springclean.xml;

import nu.xom.Element;
import springclean.domain.ApplicationContext;

class AbstractElementWrapper {
    protected final Element element;
    protected final ApplicationContext applicationContext;

    protected AbstractElementWrapper(Element beanNode, ApplicationContext applicationContext) {
        this.element = beanNode;
        this.applicationContext = applicationContext;
    }

    protected boolean hasAttribute(String name) {
        return element.getAttribute(name) != null;
    }

    protected String attributeValue(String name) {
        if (!hasAttribute(name))
            throw new XomProcessingException("Attribute " + name + " not found in " + element.toXML());
        return element.getAttributeValue(name);
    }

    protected boolean attributeValueEquals(String name, String value) {
        return hasAttribute(name) && value.equalsIgnoreCase(element.getAttributeValue(name));
    }

    @Override
    public String toString() {
        return element.toXML();
    }
}
