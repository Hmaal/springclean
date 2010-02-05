package springclean.core.generate.aliasing;

import springclean.core.generate.data.AnonymousBean;
import springclean.core.generate.data.ConstructorInjectedBean;
import springclean.core.generate.data.NoDependencyBean;

public class ApplicationContext {
    public final NoDependencyBean noDependencyBean;

    public final ConstructorInjectedBean aConstructorInjectedBean;

    public ApplicationContext() throws Exception {
        noDependencyBean = new NoDependencyBean();
        aConstructorInjectedBean = new ConstructorInjectedBean(noDependencyBean, noDependencyBean, new AnonymousBean(), 1);
    }

}
