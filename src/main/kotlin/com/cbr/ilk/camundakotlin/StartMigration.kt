package com.cbr.ilk.camundakotlin

import com.cbr.ilk.camundakotlin.processmigration.UpgradeMainFromV1ToV2
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Slf4j
class StartMigration {
    @Autowired
    private val upgradeMainFromV1ToV2: UpgradeMainFromV1ToV2? = null
    @GetMapping("/startMigration")
    fun start(): String {
        upgradeMainFromV1ToV2?.run()
        return "started"
    }
}
