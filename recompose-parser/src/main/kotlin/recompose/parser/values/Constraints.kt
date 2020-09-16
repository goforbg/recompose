/*
 * Copyright 2020 Sebastian Kaspari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package recompose.parser.values

import org.xmlpull.v1.XmlPullParser
import recompose.ast.values.Constraints
import recompose.parser.xml.id

/**
 * Parses the [Constraints] of a View inside a ConstraintLayout.
 */
internal fun XmlPullParser.constraints(): Constraints {
    return Constraints(
        // We assume the "app" namespace is used for constraints. That's probably a reasonable assumption since that
        // is also what is used in all the docs. But technically an app could use a different namespace.

        // Relative Positioning Constraints
        bottomToBottom = constraintId("app:layout_constraintBottom_toBottomOf"),
        bottomToTop = constraintId("app:layout_constraintBottom_toTopOf"),
        endToEnd = constraintId("app:layout_constraintEnd_toEndOf"),
        endToStart = constraintId("app:layout_constraintEnd_toStartOf"),
        leftToLeft = constraintId("app:layout_constraintLeft_toLeftOf"),
        leftToRight = constraintId("app:layout_constraintLeft_toRightOf"),
        rightToLeft = constraintId("app:layout_constraintRight_toLeftOf"),
        rightToRight = constraintId("app:layout_constraintRight_toRightOf"),
        startToEnd = constraintId("app:layout_constraintStart_toEndOf"),
        startToStart = constraintId("app:layout_constraintStart_toStartOf"),
        topToBottom = constraintId("app:layout_constraintTop_toBottomOf"),
        topToTop = constraintId("app:layout_constraintTop_toTopOf"),
    )
}

private fun XmlPullParser.constraintId(name: String): Constraints.Id? {
    return if (getAttributeValue(null, name) == "parent") {
        Constraints.Id.Parent
    } else {
        id(name)?.let { Constraints.Id.View(it) }
    }
}