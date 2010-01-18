package springclean.generate;

import static com.google.common.collect.Lists.newArrayList;
import org.daisychain.source.AClass;
import static org.daisychain.source.HasImports.ImportExtractor.extractImportsFrom;
import org.daisychain.source.Instance;
import org.daisychain.source.body.AssignableStatement;
import org.daisychain.source.body.MethodCall;
import org.daisychain.source.util.IndentingStringWriter;
import springclean.domain.Bean;
import springclean.domain.ConstructorArg;
import springclean.domain.Property;
import springclean.domain.Reference;
import static springclean.generate.ContextElement.DependencyExtractor.allDependenciesOf;
import springclean.xml.SimpleReference;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class FactoryBeanConstructionStrategy implements ConstructionStrategy {
    private final Reference reference;
    private final Bean bean;

    public FactoryBeanConstructionStrategy(Reference reference, Bean bean) {
        this.reference = reference;
        this.bean = bean;
    }

    public AssignableStatement asStatement() {
        final List<AssignableStatement> argumentStatements = newArrayList();

        int count=0;
        for (ConstructorArg constructorArg : bean.constructorArgs()) {
            argumentStatements.add(constructorArg.referencedObject().asContextElement(bean.factoryMethod().parameters().get(count++).instance.aClass).asStatement());
        }

        return new AssignableStatement() {
            public Set<AClass> getImports() {
                return extractImportsFrom(argumentStatements);
            }

            public void appendSource(IndentingStringWriter writer) throws IOException {
                new MethodCall(new org.daisychain.source.Reference(reference.id().value), bean.factoryMethod(), argumentStatements).appendSource(writer);
            }
        };

    }

    public Set<Instance> dependencies() {
        final List<ContextElement> injectedDependencies = newArrayList();
        for (Property property : bean.setterDependencies()) {
            injectedDependencies.add(property.referencedObject().asContextElement(bean.setter(property).parameters().get(0).instance.aClass));
        }
        return allDependenciesOf(injectedDependencies);
    }
}
