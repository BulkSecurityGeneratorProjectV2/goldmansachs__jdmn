
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"bkm.ftl", "BureauCallTypeTable"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "BureauCallTypeTable",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.DECISION_TABLE,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE,
    rulesCount = 3
)
public class BureauCallTypeTable extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "BureauCallTypeTable",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.BUSINESS_KNOWLEDGE_MODEL,
        com.gs.dmn.runtime.annotation.ExpressionKind.DECISION_TABLE,
        com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE,
        3
    );

    private static class BureauCallTypeTableLazyHolder {
        static final BureauCallTypeTable INSTANCE = new BureauCallTypeTable();
    }
    public static BureauCallTypeTable instance() {
        return BureauCallTypeTableLazyHolder.INSTANCE;
    }

    private BureauCallTypeTable() {
    }

    @java.lang.Override()
    public String apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(input_.get("'Pre-bureauRiskCategory'"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'BureauCallTypeTable'", e);
            return null;
        }
    }

    public String apply(String preBureauRiskCategory, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start BKM 'BureauCallTypeTable'
            long bureauCallTypeTableStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments bureauCallTypeTableArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            bureauCallTypeTableArguments_.put("'Pre-bureauRiskCategory'", preBureauRiskCategory);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, bureauCallTypeTableArguments_);

            // Evaluate BKM 'BureauCallTypeTable'
            String output_ = lambda.apply(preBureauRiskCategory, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End BKM 'BureauCallTypeTable'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, bureauCallTypeTableArguments_, output_, (System.currentTimeMillis() - bureauCallTypeTableStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'BureauCallTypeTable' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<String> lambda =
        new com.gs.dmn.runtime.LambdaExpression<String>() {
            public String apply(Object... args_) {
                String preBureauRiskCategory = 0 < args_.length ? (String) args_[0] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 1 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[1] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 2 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[2] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 3 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[3] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 4 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[4] : null;

                // Apply rules and collect results
                com.gs.dmn.runtime.RuleOutputList ruleOutputList_ = new com.gs.dmn.runtime.RuleOutputList();
                ruleOutputList_.add(rule0(preBureauRiskCategory, annotationSet_, eventListener_, externalExecutor_, cache_));
                ruleOutputList_.add(rule1(preBureauRiskCategory, annotationSet_, eventListener_, externalExecutor_, cache_));
                ruleOutputList_.add(rule2(preBureauRiskCategory, annotationSet_, eventListener_, externalExecutor_, cache_));

                // Return results based on hit policy
                String output_;
                if (ruleOutputList_.noMatchedRules()) {
                    // Default value
                    output_ = null;
                } else {
                    com.gs.dmn.runtime.RuleOutput ruleOutput_ = ruleOutputList_.applySingle(com.gs.dmn.runtime.annotation.HitPolicy.UNIQUE);
                    output_ = ruleOutput_ == null ? null : ((BureauCallTypeTableRuleOutput)ruleOutput_).getBureauCallTypeTable();
                }

                return output_;
            }
    };

    @com.gs.dmn.runtime.annotation.Rule(index = 0, annotation = "")
    public com.gs.dmn.runtime.RuleOutput rule0(String preBureauRiskCategory, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(0, "");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        BureauCallTypeTableRuleOutput output_ = new BureauCallTypeTableRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            booleanOr((stringEqual(preBureauRiskCategory, "HIGH")), (stringEqual(preBureauRiskCategory, "MEDIUM")))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setBureauCallTypeTable("FULL");

            // Add annotation
            annotationSet_.addAnnotation("BureauCallTypeTable", 0, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 1, annotation = "")
    public com.gs.dmn.runtime.RuleOutput rule1(String preBureauRiskCategory, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(1, "");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        BureauCallTypeTableRuleOutput output_ = new BureauCallTypeTableRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            (stringEqual(preBureauRiskCategory, "LOW"))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setBureauCallTypeTable("MINI");

            // Add annotation
            annotationSet_.addAnnotation("BureauCallTypeTable", 1, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

    @com.gs.dmn.runtime.annotation.Rule(index = 2, annotation = "")
    public com.gs.dmn.runtime.RuleOutput rule2(String preBureauRiskCategory, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Rule metadata
        com.gs.dmn.runtime.listener.Rule drgRuleMetadata = new com.gs.dmn.runtime.listener.Rule(2, "");

        // Rule start
        eventListener_.startRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

        // Apply rule
        BureauCallTypeTableRuleOutput output_ = new BureauCallTypeTableRuleOutput(false);
        if (ruleMatches(eventListener_, drgRuleMetadata,
            booleanOr((stringEqual(preBureauRiskCategory, "VERY LOW")), (stringEqual(preBureauRiskCategory, "DECLINE")))
        )) {
            // Rule match
            eventListener_.matchRule(DRG_ELEMENT_METADATA, drgRuleMetadata);

            // Compute output
            output_.setMatched(true);
            output_.setBureauCallTypeTable("NONE");

            // Add annotation
            annotationSet_.addAnnotation("BureauCallTypeTable", 2, "");
        }

        // Rule end
        eventListener_.endRule(DRG_ELEMENT_METADATA, drgRuleMetadata, output_);

        return output_;
    }

}
