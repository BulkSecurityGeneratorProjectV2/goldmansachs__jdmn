package decisiontables;

import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "dateCompare2"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "decisiontables",
    name = "dateCompare2",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.DECISION_TABLE,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE,
    rulesCount = 2
)
public class DateCompare2 extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "decisiontables",
        "dateCompare2",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.DECISION_TABLE,
        com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE,
        2
    );

    public DateCompare2() {
    }

    public Boolean apply(String decisioninputs_dateD, String decisioninputs_dateE, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_) {
        try {
            return apply((decisioninputs_dateD != null ? date(decisioninputs_dateD) : null), (decisioninputs_dateE != null ? date(decisioninputs_dateE) : null), annotationSet_, new com.gs.dmn.runtime.listener.LoggingEventListener(LOGGER), new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor(), new com.gs.dmn.runtime.cache.DefaultCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'DateCompare2'", e);
            return null;
        }
    }

    public Boolean apply(String decisioninputs_dateD, String decisioninputs_dateE, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((decisioninputs_dateD != null ? date(decisioninputs_dateD) : null), (decisioninputs_dateE != null ? date(decisioninputs_dateE) : null), annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'DateCompare2'", e);
            return null;
        }
    }

    public Boolean apply(javax.xml.datatype.XMLGregorianCalendar decisioninputs_dateD, javax.xml.datatype.XMLGregorianCalendar decisioninputs_dateE, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_) {
        return apply(decisioninputs_dateD, decisioninputs_dateE, annotationSet_, new com.gs.dmn.runtime.listener.LoggingEventListener(LOGGER), new com.gs.dmn.runtime.external.DefaultExternalFunctionExecutor(), new com.gs.dmn.runtime.cache.DefaultCache());
    }

    public Boolean apply(javax.xml.datatype.XMLGregorianCalendar decisioninputs_dateD, javax.xml.datatype.XMLGregorianCalendar decisioninputs_dateE, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'dateCompare2'
            long dateCompare2StartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments dateCompare2Arguments_ = new com.gs.dmn.runtime.listener.Arguments();
            dateCompare2Arguments_.put("decisionInputs.dateD", decisioninputs_dateD);
            dateCompare2Arguments_.put("decisionInputs.dateE", decisioninputs_dateE);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, dateCompare2Arguments_);

            // Evaluate decision 'dateCompare2'
            Boolean output_ = lambda.apply(decisioninputs_dateD, decisioninputs_dateE, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'dateCompare2'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, dateCompare2Arguments_, output_, (System.currentTimeMillis() - dateCompare2StartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'dateCompare2' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<Boolean> lambda =
        new com.gs.dmn.runtime.LambdaExpression<Boolean>() {
            public Boolean apply(Object... args_) {
                javax.xml.datatype.XMLGregorianCalendar decisioninputs_dateD = 0 < args_.length ? (javax.xml.datatype.XMLGregorianCalendar) args_[0] : null;
                javax.xml.datatype.XMLGregorianCalendar decisioninputs_dateE = 1 < args_.length ? (javax.xml.datatype.XMLGregorianCalendar) args_[1] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 2 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[2] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 3 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[3] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 4 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[4] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 5 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[5] : null;

                // Apply rules and collect results
                com.gs.dmn.runtime.RuleOutputList ruleOutputList_ = new com.gs.dmn.runtime.RuleOutputList();
                ruleOutputList_.add(rule0(decisioninputs_dateD, decisioninputs_dateE, annotationSet_, eventListener_, externalExecutor_, cache_));
                ruleOutputList_.add(rule1(decisioninputs_dateD, decisioninputs_dateE, annotationSet_, eventListener_, externalExecutor_, cache_));

                // Return results based on hit policy
                Boolean output_;
                if (ruleOutputList_.noMatchedRules()) {
                    // Default value
                    output_ = null;
                } else {
                    com.gs.dmn.runtime.RuleOutput ruleOutput_ = ruleOutputList_.applySingle(com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE);
                    output_ = ruleOutput_ == null ? null : ((DateCompare2RuleOutput)ruleOutput_).getDateCompare2();
                }

                return output_;
            }
    };

    @com.gs.dmn.runtime.annotation.Rule(index = 0, annotation = "")
    public com.gs.dmn.runtime.RuleOutput rule0(javax.xml.datatype.XMLGregorianCalendar decisioninputs_dateD, javax.xml.datatype.XMLGregorianCalendar decisioninputs_dateE, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(0, "");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DateCompare2RuleOutput output_ = new DateCompare2RuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (dateGreaterThan(decisioninputs_dateD, decisioninputs_dateE))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDateCompare2(Boolean.TRUE);

            // Add annotation
            annotationSet_.addAnnotation("dateCompare2", 0, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 1, annotation = "")
    public com.gs.dmn.runtime.RuleOutput rule1(javax.xml.datatype.XMLGregorianCalendar decisioninputs_dateD, javax.xml.datatype.XMLGregorianCalendar decisioninputs_dateE, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(1, "");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        DateCompare2RuleOutput output_ = new DateCompare2RuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (dateLessEqualThan(decisioninputs_dateD, decisioninputs_dateE))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setDateCompare2(Boolean.FALSE);

            // Add annotation
            annotationSet_.addAnnotation("dateCompare2", 1, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

}