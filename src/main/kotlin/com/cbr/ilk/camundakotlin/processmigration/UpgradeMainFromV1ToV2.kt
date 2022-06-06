package com.cbr.ilk.camundakotlin.processmigration

import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.variable.Variables
import org.camunda.bpm.engine.variable.value.TypedValue
import org.camunda.bpm.extension.migration.Migrator
import org.camunda.bpm.extension.migration.plan.MigrationPlan
import org.camunda.bpm.extension.migration.plan.ProcessDefinitionSpec
import org.camunda.bpm.extension.migration.plan.step.model.MigrationPlanFactory
import org.camunda.bpm.extension.migration.plan.step.model.ModelStep
import org.camunda.bpm.extension.migration.plan.step.variable.Conversion
import org.camunda.bpm.extension.migration.plan.step.variable.VariableStep
import org.camunda.bpm.extension.migration.plan.step.variable.strategy.ReadConstantValue
import org.camunda.bpm.extension.migration.plan.step.variable.strategy.ReadProcessVariable
import org.camunda.bpm.extension.migration.plan.step.variable.strategy.WriteProcessVariable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class UpgradeMainFromV1ToV2 {
    @Autowired
    private val processEngine: ProcessEngine? = null

    @Autowired
    private val runtimeService: RuntimeService? = null
    private val camundaMigrationPlanFactory =
        MigrationPlanFactory { source: String?, target: String? ->
            runtimeService!!
                .createMigrationPlan(source, target)
                .mapActivities("Task_AB", "Task_A")
                .build()
        }
    private val modelStep = ModelStep(camundaMigrationPlanFactory)
    private val v1 = ProcessDefinitionSpec.builder()
        .versionTag("v1")
        .processDefinitionKey("main")
        .build()
    private val v2 = ProcessDefinitionSpec.builder()
        .versionTag("v2")
        .processDefinitionKey("main-redeploy")
        .build()
    private val convertInvoiceNumber = Conversion { originalTypedValue: TypedValue ->
        val invoiceNumber = originalTypedValue.value.toString().substring(4)
        Variables.longValue(java.lang.Long.valueOf(invoiceNumber))
    }
    private val rename_formField_19huq07_to_invoiceNumber = VariableStep.builder()
        .readStrategy(ReadProcessVariable())
        .writeStrategy(WriteProcessVariable())
        .sourceVariableName("FormField_19huq07")
        .targetVariableName("invoiceNumber")
        .conversion(convertInvoiceNumber)
        .build()
    private val create_variable_numPieces = VariableStep.builder()
        .readStrategy(ReadConstantValue(Variables.longValue(1L)))
        .writeStrategy(WriteProcessVariable())
        .sourceVariableName("numPieces")
        .build()
    private val migrationPlan = MigrationPlan.builder()
        .from(v1).to(v2)
        .step(modelStep)
        .step(rename_formField_19huq07_to_invoiceNumber)
        .step(create_variable_numPieces)
        .build()

    fun run() {
        Migrator(processEngine).migrate(migrationPlan)
    }
}