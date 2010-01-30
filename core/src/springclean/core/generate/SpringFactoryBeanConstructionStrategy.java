package springclean.core.generate;

import org.daisychain.source.AClass;
import org.daisychain.source.Instance;
import org.daisychain.source.body.AssignableStatement;
import org.daisychain.source.body.Cast;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.Set;

public class SpringFactoryBeanConstructionStrategy implements ConstructionStrategy {
    private final AClass castClass;
    private final ConstructionStrategy innerConstructionStrategy;

    public SpringFactoryBeanConstructionStrategy(AClass castClass, ConstructionStrategy innerConstructionStrategy) {
        this.castClass = castClass;
        this.innerConstructionStrategy = innerConstructionStrategy;
    }

    public AssignableStatement asStatement() {
        final AssignableStatement innerStatement = new Cast(castClass, innerConstructionStrategy.asStatement());
        return new AssignableStatement() {
            public Set<AClass> getImports() {
                return innerStatement.getImports();
            }

            public void appendSource(IndentingStringWriter indentingStringWriter) throws IOException {
                innerStatement.appendSource(indentingStringWriter);
                indentingStringWriter.append(".getObject()");
            }
        };
    }

    public Set<Instance> dependencies() {
        return innerConstructionStrategy.dependencies();
    }
}