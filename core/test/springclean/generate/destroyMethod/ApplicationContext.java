package springclean.generate.destroyMethod;

import springclean.Stoppable;
import springclean.generate.data.DestroyableBean;

public class ApplicationContext implements Stoppable {
    public final DestroyableBean destroyableBean;

    public ApplicationContext() {
        destroyableBean = new DestroyableBean();
    }

    public void stop() throws Exception {
        destroyableBean.destroyMethod();
    }

}