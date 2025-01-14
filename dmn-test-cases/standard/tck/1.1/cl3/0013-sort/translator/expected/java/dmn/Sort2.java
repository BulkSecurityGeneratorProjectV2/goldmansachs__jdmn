
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "sort2"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "sort2",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class Sort2 extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "sort2",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public Sort2() {
    }

    @java.lang.Override()
    public List<type.TRow> apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(input_.get("tableB"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'Sort2'", e);
            return null;
        }
    }

    public List<type.TRow> apply(String tableB, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((tableB != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(tableB, new com.fasterxml.jackson.core.type.TypeReference<List<type.TRow>>() {}) : null), annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'Sort2'", e);
            return null;
        }
    }

    public List<type.TRow> apply(List<type.TRow> tableB, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'sort2'
            long sort2StartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments sort2Arguments_ = new com.gs.dmn.runtime.listener.Arguments();
            sort2Arguments_.put("tableB", tableB);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, sort2Arguments_);

            // Evaluate decision 'sort2'
            List<type.TRow> output_ = lambda.apply(tableB, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'sort2'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, sort2Arguments_, output_, (System.currentTimeMillis() - sort2StartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'sort2' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<List<type.TRow>> lambda =
        new com.gs.dmn.runtime.LambdaExpression<List<type.TRow>>() {
            public List<type.TRow> apply(Object... args_) {
                List<type.TRow> tableB = 0 < args_.length ? (List<type.TRow>) args_[0] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 1 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[1] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 2 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[2] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 3 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[3] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 4 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[4] : null;

                return sort(tableB, new com.gs.dmn.runtime.LambdaExpression<Boolean>() {public Boolean apply(Object... args_) {type.TRow x = (type.TRow)args_[0]; type.TRow y = (type.TRow)args_[1];return numericLessThan(((java.math.BigDecimal)(x != null ? x.getCol2() : null)), ((java.math.BigDecimal)(y != null ? y.getCol2() : null)));}}).stream().map(x -> type.TRow.toTRow(x)).collect(Collectors.toList());
            }
        };
}
