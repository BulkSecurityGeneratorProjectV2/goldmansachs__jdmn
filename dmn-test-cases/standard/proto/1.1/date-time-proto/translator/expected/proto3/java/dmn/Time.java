
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "Time"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "Time",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class Time extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "Time",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public static java.util.Map<String, Object> requestToMap(proto.TimeRequest timeRequest_) {
        // Create arguments from Request Message
        javax.xml.datatype.XMLGregorianCalendar inputTime = com.gs.dmn.feel.lib.DefaultFEELLib.INSTANCE.time(timeRequest_.getInputTime());

        // Create map
        java.util.Map<String, Object> map_ = new java.util.LinkedHashMap<>();
        map_.put("InputTime", inputTime);
        return map_;
    }

    public static javax.xml.datatype.XMLGregorianCalendar responseToOutput(proto.TimeResponse timeResponse_) {
        // Extract and convert output
        return com.gs.dmn.feel.lib.DefaultFEELLib.INSTANCE.time(timeResponse_.getTime());
    }

    public Time() {
    }

    @java.lang.Override()
    public javax.xml.datatype.XMLGregorianCalendar apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(input_.get("InputTime"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'Time'", e);
            return null;
        }
    }

    public javax.xml.datatype.XMLGregorianCalendar apply(String inputTime, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            return apply((inputTime != null ? time(inputTime) : null), annotationSet_, eventListener_, externalExecutor_, cache_);
        } catch (Exception e) {
            logError("Cannot apply decision 'Time'", e);
            return null;
        }
    }

    public javax.xml.datatype.XMLGregorianCalendar apply(javax.xml.datatype.XMLGregorianCalendar inputTime, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start decision 'Time'
            long timeStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments timeArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            timeArguments_.put("InputTime", inputTime);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, timeArguments_);

            // Evaluate decision 'Time'
            javax.xml.datatype.XMLGregorianCalendar output_ = lambda.apply(inputTime, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End decision 'Time'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, timeArguments_, output_, (System.currentTimeMillis() - timeStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'Time' evaluation", e);
            return null;
        }
    }

    public proto.TimeResponse apply(proto.TimeRequest timeRequest_, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        // Create arguments from Request Message
        javax.xml.datatype.XMLGregorianCalendar inputTime = time(timeRequest_.getInputTime());

        // Invoke apply method
        javax.xml.datatype.XMLGregorianCalendar output_ = apply(inputTime, annotationSet_, eventListener_, externalExecutor_, cache_);

        // Convert output to Response Message
        proto.TimeResponse.Builder builder_ = proto.TimeResponse.newBuilder();
        String outputProto_ = string(output_);
        builder_.setTime(outputProto_);
        return builder_.build();
    }

    public com.gs.dmn.runtime.LambdaExpression<javax.xml.datatype.XMLGregorianCalendar> lambda =
        new com.gs.dmn.runtime.LambdaExpression<javax.xml.datatype.XMLGregorianCalendar>() {
            public javax.xml.datatype.XMLGregorianCalendar apply(Object... args_) {
                javax.xml.datatype.XMLGregorianCalendar inputTime = 0 < args_.length ? (javax.xml.datatype.XMLGregorianCalendar) args_[0] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 1 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[1] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 2 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[2] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 3 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[3] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 4 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[4] : null;

                return inputTime;
            }
        };
}
