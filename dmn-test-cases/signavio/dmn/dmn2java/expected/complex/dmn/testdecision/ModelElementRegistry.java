
public class ModelElementRegistry extends com.gs.dmn.runtime.discovery.ModelElementRegistry {
    public ModelElementRegistry() {
        // Register elements from model 'TestDecision'
        register("StringInput", "StringInput");
        register("Test", "Test");
    }
}
