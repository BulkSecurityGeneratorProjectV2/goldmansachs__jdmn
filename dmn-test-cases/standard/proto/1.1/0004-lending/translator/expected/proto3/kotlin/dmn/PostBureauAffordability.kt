
import java.util.*
import java.util.stream.Collectors

@javax.annotation.Generated(value = ["decision.ftl", "'Post-bureauAffordability'"])
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "'Post-bureauAffordability'",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.INVOCATION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
class PostBureauAffordability(val postBureauRiskCategory : PostBureauRiskCategory = PostBureauRiskCategory(), val requiredMonthlyInstallment : RequiredMonthlyInstallment = RequiredMonthlyInstallment()) : com.gs.dmn.runtime.DefaultDMNBaseDecision() {
    override fun apply(input_: MutableMap<String, String>, context_: com.gs.dmn.runtime.ExecutionContext): Boolean? {
        try {
            return apply(input_.get("ApplicantData"), input_.get("BureauData"), input_.get("RequestedProduct"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache())
        } catch (e: Exception) {
            logError("Cannot apply decision 'PostBureauAffordability'", e)
            return null
        }
    }

    fun apply(applicantData: String?, bureauData: String?, requestedProduct: String?, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): Boolean? {
        return try {
            apply(applicantData?.let({ com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(it, object : com.fasterxml.jackson.core.type.TypeReference<type.TApplicantDataImpl>() {}) }), bureauData?.let({ com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(it, object : com.fasterxml.jackson.core.type.TypeReference<type.TBureauDataImpl>() {}) }), requestedProduct?.let({ com.gs.dmn.serialization.JsonSerializer.OBJECT_MAPPER.readValue(it, object : com.fasterxml.jackson.core.type.TypeReference<type.TRequestedProductImpl>() {}) }), annotationSet_, eventListener_, externalExecutor_, cache_)
        } catch (e: Exception) {
            logError("Cannot apply decision 'PostBureauAffordability'", e)
            null
        }
    }

    fun apply(applicantData: type.TApplicantData?, bureauData: type.TBureauData?, requestedProduct: type.TRequestedProduct?, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): Boolean? {
        try {
            // Start decision ''Post-bureauAffordability''
            val postBureauAffordabilityStartTime_ = System.currentTimeMillis()
            val postBureauAffordabilityArguments_ = com.gs.dmn.runtime.listener.Arguments()
            postBureauAffordabilityArguments_.put("ApplicantData", applicantData)
            postBureauAffordabilityArguments_.put("BureauData", bureauData)
            postBureauAffordabilityArguments_.put("RequestedProduct", requestedProduct)
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, postBureauAffordabilityArguments_)

            // Evaluate decision ''Post-bureauAffordability''
            val output_: Boolean? = evaluate(applicantData, bureauData, requestedProduct, annotationSet_, eventListener_, externalExecutor_, cache_)

            // End decision ''Post-bureauAffordability''
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, postBureauAffordabilityArguments_, output_, (System.currentTimeMillis() - postBureauAffordabilityStartTime_))

            return output_
        } catch (e: Exception) {
            logError("Exception caught in ''Post-bureauAffordability'' evaluation", e)
            return null
        }
    }

    fun apply(postBureauAffordabilityRequest_: proto.PostBureauAffordabilityRequest, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): proto.PostBureauAffordabilityResponse {
        // Create arguments from Request Message
        val applicantData: type.TApplicantData? = type.TApplicantData.toTApplicantData(postBureauAffordabilityRequest_.getApplicantData())
        val bureauData: type.TBureauData? = type.TBureauData.toTBureauData(postBureauAffordabilityRequest_.getBureauData())
        val requestedProduct: type.TRequestedProduct? = type.TRequestedProduct.toTRequestedProduct(postBureauAffordabilityRequest_.getRequestedProduct())

        // Invoke apply method
        val output_: Boolean? = apply(applicantData, bureauData, requestedProduct, annotationSet_, eventListener_, externalExecutor_, cache_)

        // Convert output to Response Message
        val builder_: proto.PostBureauAffordabilityResponse.Builder = proto.PostBureauAffordabilityResponse.newBuilder()
        val outputProto_ = (if (output_ == null) false else output_!!)
        builder_.setPostBureauAffordability(outputProto_)
        return builder_.build()
    }

    private inline fun evaluate(applicantData: type.TApplicantData?, bureauData: type.TBureauData?, requestedProduct: type.TRequestedProduct?, annotationSet_: com.gs.dmn.runtime.annotation.AnnotationSet, eventListener_: com.gs.dmn.runtime.listener.EventListener, externalExecutor_: com.gs.dmn.runtime.external.ExternalFunctionExecutor, cache_: com.gs.dmn.runtime.cache.Cache): Boolean? {
        // Apply child decisions
        val postBureauRiskCategory: String? = this@PostBureauAffordability.postBureauRiskCategory.apply(applicantData, bureauData, annotationSet_, eventListener_, externalExecutor_, cache_)
        val requiredMonthlyInstallment: java.math.BigDecimal? = this@PostBureauAffordability.requiredMonthlyInstallment.apply(requestedProduct, annotationSet_, eventListener_, externalExecutor_, cache_)

        return AffordabilityCalculation.instance().apply(applicantData?.let({ it.monthly as type.Monthly? })?.let({ it.income as java.math.BigDecimal? }), applicantData?.let({ it.monthly as type.Monthly? })?.let({ it.repayments as java.math.BigDecimal? }), applicantData?.let({ it.monthly as type.Monthly? })?.let({ it.expenses as java.math.BigDecimal? }), postBureauRiskCategory, requiredMonthlyInstallment, annotationSet_, eventListener_, externalExecutor_, cache_) as Boolean?
    }

    companion object {
        val DRG_ELEMENT_METADATA : com.gs.dmn.runtime.listener.DRGElement = com.gs.dmn.runtime.listener.DRGElement(
            "",
            "'Post-bureauAffordability'",
            "",
            com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
            com.gs.dmn.runtime.annotation.ExpressionKind.INVOCATION,
            com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
            -1
        )

        @JvmStatic
        fun requestToMap(postBureauAffordabilityRequest_: proto.PostBureauAffordabilityRequest): kotlin.collections.Map<String, Any?> {
            // Create arguments from Request Message
            val applicantData: type.TApplicantData? = type.TApplicantData.toTApplicantData(postBureauAffordabilityRequest_.getApplicantData())
            val bureauData: type.TBureauData? = type.TBureauData.toTBureauData(postBureauAffordabilityRequest_.getBureauData())
            val requestedProduct: type.TRequestedProduct? = type.TRequestedProduct.toTRequestedProduct(postBureauAffordabilityRequest_.getRequestedProduct())

            // Create map
            val map_: kotlin.collections.MutableMap<String, Any?> = mutableMapOf()
            map_.put("ApplicantData", applicantData)
            map_.put("BureauData", bureauData)
            map_.put("RequestedProduct", requestedProduct)
            return map_
        }

        @JvmStatic
        fun responseToOutput(postBureauAffordabilityResponse_: proto.PostBureauAffordabilityResponse): Boolean? {
            // Extract and convert output
            return postBureauAffordabilityResponse_.getPostBureauAffordability()
        }
    }
}
