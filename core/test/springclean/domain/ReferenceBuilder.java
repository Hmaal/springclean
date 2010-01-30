package springclean.domain;

import org.daisychain.source.AClass;
import org.daisychain.source.ExistingClass;
import springclean.core.generate.ContextElementBuilder;
import static springclean.domain.SpringId.springId;
import springclean.generate.ContextElement;

public class ReferenceBuilder {
    private SpringId springId = springId("springId");
    private ContextElement contextElement = ContextElementBuilder.aContextElement().build();
    private AClass aClass = new ExistingClass(String.class);

    private ReferenceBuilder() {
    }

    public static ReferenceBuilder aReference() {
        return new ReferenceBuilder();
    }

    public ReferenceBuilder withIdentity(SpringId springId) {
        this.springId = springId;
        return this;
    }

    public ReferenceBuilder withClass(AClass aClass) {
        this.aClass = aClass;
        return this;
    }

    public ReferenceBuilder whichProducesContextElement(ContextElement contextElement) {
        this.contextElement = contextElement;
        return this;
    }

    public Reference build() {
        return new Reference() {
            public SpringId id() {
                return springId;
            }

            public ContextElement asContextElement(AClass aClass) {
                return contextElement;
            }

            public AClass clazz() {
                return aClass;
            }
        };
    }
}