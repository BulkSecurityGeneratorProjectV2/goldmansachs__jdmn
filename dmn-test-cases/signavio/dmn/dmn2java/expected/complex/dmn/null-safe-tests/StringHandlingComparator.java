
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"signavio-decision.ftl", "stringHandlingComparator"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "stringHandlingComparator",
    label = "stringHandlingComparator",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class StringHandlingComparator extends com.gs.dmn.signavio.runtime.DefaultSignavioBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "stringHandlingComparator",
        "stringHandlingComparator",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    private final StringHandling stringHandling;

    public StringHandlingComparator() {
        this(new StringHandling());
    }

    public StringHandlingComparator(StringHandling stringHandling) {
        this.stringHandling = stringHandling;
    }

    @java.lang.Override()
    public Boolean apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(input_.get("numberA"), input_.get("numberB"), input_.get("stringList"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'StringHandlingComparator'", e);
            return null;
        }
    }

    public Boolean apply(String numberA, String numberB, String stringList, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((numberA != null ? number(numberA) : null), (numberB != null ? number(numberB) : null), (stringList != null ? com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(stringList, new com.fasterxml.jackson.core.type.TypeReference<List<String>>() {}) : null), annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'StringHandlingComparator'", e);
            return null;
        }
    }

    public Boolean apply(java.math.BigDecimal numberA, java.math.BigDecimal numberB, List<String> stringList, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'stringHandlingComparator'
            long stringHandlingComparatorStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments stringHandlingComparatorArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            stringHandlingComparatorArguments_.put("numberA", numberA);
            stringHandlingComparatorArguments_.put("numberB", numberB);
            stringHandlingComparatorArguments_.put("stringList", stringList);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, stringHandlingComparatorArguments_);

            // Evaluate decision 'stringHandlingComparator'
            Boolean output_ = evaluate(numberA, numberB, stringList, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'stringHandlingComparator'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, stringHandlingComparatorArguments_, output_, (System.currentTimeMillis() - stringHandlingComparatorStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'stringHandlingComparator' evaluation", e);
            return null;
        }
    }

    protected Boolean evaluate(java.math.BigDecimal numberA, java.math.BigDecimal numberB, List<String> stringList, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Apply child decisions
        String stringHandling = this.stringHandling.apply(numberA, numberB, stringList, annotationSet_, eventListener_, externalExecutor_, cache_);

        return booleanAnd(booleanAnd(booleanAnd(booleanAnd(booleanAnd(booleanAnd(isAlpha(stringHandling), isAlphanumeric(stringHandling)), isNumeric(stringHandling)), isSpaces(stringHandling)), startsWith(stringHandling, stringHandling)), endsWith(stringHandling, stringHandling)), contains(stringHandling, stringHandling));
    }
}
