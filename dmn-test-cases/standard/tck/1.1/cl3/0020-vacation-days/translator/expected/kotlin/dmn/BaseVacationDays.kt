
import java.util.*
import java.util.stream.Collectors

@javax.annotation.Generated(value = ["decision.ftl", "'Base Vacation Days'"])
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "'Base Vacation Days'",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
class BaseVacationDays() : com.gs.dmn.runtime.DefaultDMNBaseDecision() {
    override fun apply(input_: MutableMap<String, String>, context_: com.gs.dmn.runtime.ExecutionContext): java.math.BigDecimal? {
        try {
            return apply(context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache())
        } catch (e: Exception) {
            logError("Cannot apply decision 'BaseVacationDays'", e)
            return null
        }
    }

    fun apply(annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): java.math.BigDecimal? {
        try {
            // Start decision ''Base Vacation Days''
            val baseVacationDaysStartTime_ = System.currentTimeMillis()
            val baseVacationDaysArguments_ = com.gs.dmn.runtime.listener.Arguments()
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, baseVacationDaysArguments_)

            // Evaluate decision ''Base Vacation Days''
            val output_: java.math.BigDecimal? = evaluate(annotationSet_, eventListener_, externalExecutor_, cache_)

            // End decision ''Base Vacation Days''
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, baseVacationDaysArguments_, output_, (System.currentTimeMillis() - baseVacationDaysStartTime_))

            return output_
        } catch (e: Exception) {
            logError("Exception caught in ''Base Vacation Days'' evaluation", e)
            return null
        }
    }

    private inline fun evaluate(annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): java.math.BigDecimal? {
        return number("22") as java.math.BigDecimal?
    }

    companion object {
        val DRG_ELEMENT_METADATA : com.gs.dmn.runtime.listener.DRGElement = com.gs.dmn.runtime.listener.DRGElement(
            "",
            "'Base Vacation Days'",
            "",
            com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
            com.gs.dmn.runtime.annotation.ExpressionKind.LITERAL_EXPRESSION,
            com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
            -1
        )
    }
}
