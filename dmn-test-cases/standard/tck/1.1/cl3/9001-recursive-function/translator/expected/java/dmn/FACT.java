
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"bkm.ftl", "FACT"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "FACT",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class FACT extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "FACT",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    private static class FACTLazyHolder {
        static final FACT INSTANCE = new FACT();
    }
    public static FACT instance() {
        return FACTLazyHolder.INSTANCE;
    }

    private FACT() {
    }

    @java.lang.Override()
    public java.math.BigDecimal apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply((input_.get("n") != null ? number(input_.get("n")) : null), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'FACT'", e);
            return null;
        }
    }

    public java.math.BigDecimal apply(java.math.BigDecimal n, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start BKM 'FACT'
            long fACTStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments fACTArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            fACTArguments_.put("n", n);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, fACTArguments_);

            // Evaluate BKM 'FACT'
            java.math.BigDecimal output_ = lambda.apply(n, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End BKM 'FACT'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, fACTArguments_, output_, (System.currentTimeMillis() - fACTStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'FACT' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal> lambda =
        new com.gs.dmn.runtime.LambdaExpression<java.math.BigDecimal>() {
            public java.math.BigDecimal apply(Object... args_) {
                java.math.BigDecimal n = 0 < args_.length ? (java.math.BigDecimal) args_[0] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 1 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[1] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 2 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[2] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 3 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[3] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 4 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[4] : null;

                return (booleanEqual(numericLessThan(n, number("0")), Boolean.TRUE)) ? null : (booleanEqual(numericEqual(n, number("0")), Boolean.TRUE)) ? number("1") : numericMultiply(n, FACT.instance().apply(numericSubtract(n, number("1")), annotationSet_, eventListener_, externalExecutor_, cache_));
            }
        };
}
