
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "incorrect_003"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "incorrect_003",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.CONTEXT,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class Incorrect_003 extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "incorrect_003",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.CONTEXT,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public Incorrect_003() {
    }

    public java.math.BigDecimal apply(com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_) {
        return apply(annotationSet_, new com.gs.dmn.runtime.listener.LoggingEventListener(LOGGER), new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor(), new com.gs.dmn.runtime.cache.DefaultCache());
    }

    public java.math.BigDecimal apply(com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'incorrect_003'
            long incorrect_003StartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments incorrect_003Arguments_ = new com.gs.dmn.runtime.listener.Arguments();
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, incorrect_003Arguments_);

            // Evaluate decision 'incorrect_003'
            java.math.BigDecimal output_ = lambda.apply(annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'incorrect_003'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, incorrect_003Arguments_, output_, (System.currentTimeMillis() - incorrect_003StartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'incorrect_003' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal> lambda =
        new com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal>() {
            public java.math.BigDecimal apply(Object... args_) {
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 0 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[0] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 1 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[1] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 2 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[2] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 3 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[3] : null;

                com.gs.dmn.runtime.external.JavaExternalFunction<java.math.BigDecimal> mathMaxString = new com.gs.dmn.runtime.external.JavaExternalFunction<>(new com.gs.dmn.runtime.external.JavaFunctionInfo("java.lang.Math", "max", Arrays.asList("java.lang.String", "java.lang.String")), externalExecutor_, java.math.BigDecimal.class);
                return mathMaxString.apply("123", "456");
            }
        };
}