package springclean.core.xml;

import org.daisychain.source.AClass;
import org.daisychain.source.ExistingMethod;
import springclean.core.domain.ApplicationContext;
import springclean.core.domain.Reference;
import springclean.core.domain.SpringId;
import springclean.core.generate.RefContextElement;

public class SimpleReference implements Reference {
    private final SpringId id;
    private final ApplicationContext applicationContext;

    public SimpleReference(SpringId id, ApplicationContext applicationContext) {
        this.id = id;
        this.applicationContext = applicationContext;
    }

    public SpringId id() {
        return id;
    }

    public RefContextElement asContextElement(AClass aClass) {
        return new RefContextElement(this, aClass);
    }

    public AClass<ExistingMethod> clazz() {
        return applicationContext.findBean(id).clazz();
    }
}