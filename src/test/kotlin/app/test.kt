package app

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import kotlin.test.assertEquals


object TestSpec: Spek({
    given("a simple starter testcase") {
        on("addition") {
            it("should be obvious a number equals itself") {
                assertEquals(1, 1)
            }
        }
    }
})

