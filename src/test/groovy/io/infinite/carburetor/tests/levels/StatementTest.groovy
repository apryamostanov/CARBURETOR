package io.infinite.carburetor.tests.levels

import io.infinite.carburetor.CarburetorTransformation

class StatementTest extends GroovyTestCase {

    void test() {
        assertScript("""
import io.infinite.carburetor.TestCarburetor
import io.infinite.carburetor.CarburetorLevel


class Foo {

    void foo() {}

    @TestCarburetor(level = CarburetorLevel.STATEMENT)
    void test(String bar) {
        if (true) {}
    }

}

new Foo().test()
""")
        assert CarburetorTransformation.lastCode == expectedCode
    }

    String expectedCode = """
testEngine.methodStart(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(10, 13, 5, 6, 'Foo', 'test'), ['bar': bar ])
try {
    testEngine.statementExecutionOpen(new io.infinite.supplies.ast.metadata.MetaDataStatement('IfStatement', 12, 12, 9, 21, 'test', 'Foo'))
    if (true) {
    } else {
    }
    testEngine.executionClose()
} 
catch (java.lang.Exception automaticException) {
    testEngine.methodException(new io.infinite.supplies.ast.metadata.MetaDataMethodNode(10, 13, 5, 6, 'Foo', 'test'), ['bar': bar ], automaticException)
    throw automaticException 
} 
finally { 
    testEngine.executionClose()} 
"""

}