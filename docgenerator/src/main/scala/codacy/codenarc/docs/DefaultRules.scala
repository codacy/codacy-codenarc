package codacy.codenarc.docs

object DefaultRules {

  // retrieved from https://codenarc.github.io/CodeNarc/StarterRuleSet-AllRulesByCategory.groovy.txt
  val list = List(
    "AbcMetric",
    "AbstractClassName",
    "AbstractClassWithPublicConstructor",
    "AbstractClassWithoutAbstractMethod",
    "AddEmptyString",
    "AssertWithinFinallyBlock",
    "AssignCollectionSort",
    "AssignCollectionUnique",
    "AssignmentInConditional",
    "BigDecimalInstantiation",
    "BitwiseOperatorInConditional",
    "BooleanGetBoolean",
    "BooleanMethodReturnsNull",
    "BracesForClass",
    "BracesForForLoop",
    "BracesForIfElse",
    "BracesForMethod",
    "BracesForTryCatchFinally",
    "BrokenNullCheck",
    "BrokenOddnessCheck",
    "BuilderMethodWithSideEffects",
    "BusyWait",
    "CatchArrayIndexOutOfBoundsException",
    "CatchError",
    "CatchException",
    "CatchIllegalMonitorStateException",
    "CatchIndexOutOfBoundsException",
    "CatchNullPointerException",
    "CatchRuntimeException",
    "CatchThrowable",
    "ChainedTest",
    "ClassForName",
    "ClassName",
    "ClassNameSameAsFilename",
    "ClassSize",
    "CloneWithoutCloneable",
    "CloneableWithoutClone",
    "CloseWithoutCloseable",
    "CollectAllIsDeprecated",
    "CompareToWithoutComparable",
    "ComparisonOfTwoConstants",
    "ComparisonWithSelf",
    "ConfusingClassNamedException",
    "ConfusingMethodName",
    "ConfusingMultipleReturns",
    "ConfusingTernary",
    "ConsecutiveLiteralAppends",
    "ConsecutiveStringConcatenation",
    "ConstantAssertExpression",
    "ConstantIfExpression",
    "ConstantTernaryExpression",
    "ConstantsOnlyInterface",
    "CouldBeElvis",
    "CoupledTestCase",
    "CrapMetric",
    "CyclomaticComplexity",
    "DeadCode",
    "DirectConnectionManagement",
    "DoubleCheckedLocking",
    "DoubleNegative",
    "DuplicateCaseStatement",
    "DuplicateImport",
    "DuplicateListLiteral",
    "DuplicateMapKey",
    "DuplicateMapLiteral",
    "DuplicateSetValue",
    "ElseBlockBraces",
    "EmptyCatchBlock",
    "EmptyClass",
    "EmptyElseBlock",
    "EmptyFinallyBlock",
    "EmptyForStatement",
    "EmptyIfStatement",
    "EmptyInstanceInitializer",
    "EmptyMethod",
    "EmptyMethodInAbstractClass",
    "EmptyStaticInitializer",
    "EmptySwitchStatement",
    "EmptySynchronizedStatement",
    "EmptyTryBlock",
    "EmptyWhileStatement",
    "EnumCustomSerializationIgnored",
    "EqualsAndHashCode",
    "EqualsOverloaded",
    "ExceptionExtendsError",
    "ExceptionNotThrown",
    "ExplicitArrayListInstantiation",
    "ExplicitCallToAndMethod",
    "ExplicitCallToCompareToMethod",
    "ExplicitCallToDivMethod",
    "ExplicitCallToEqualsMethod",
    "ExplicitCallToGetAtMethod",
    "ExplicitCallToLeftShiftMethod",
    "ExplicitCallToMinusMethod",
    "ExplicitCallToModMethod",
    "ExplicitCallToMultiplyMethod",
    "ExplicitCallToOrMethod",
    "ExplicitCallToPlusMethod",
    "ExplicitCallToPowerMethod",
    "ExplicitCallToRightShiftMethod",
    "ExplicitCallToXorMethod",
    "ExplicitGarbageCollection",
    "ExplicitHashMapInstantiation",
    "ExplicitHashSetInstantiation",
    "ExplicitLinkedHashMapInstantiation",
    "ExplicitLinkedListInstantiation",
    "ExplicitStackInstantiation",
    "ExplicitTreeSetInstantiation",
    "FactoryMethodName",
    "FieldName",
    "FileCreateTempFile",
    "FinalClassWithProtectedMember",
    "ForLoopShouldBeWhileLoop",
    "ForStatementBraces",
    "GStringAsMapKey",
    "GStringExpressionWithinString",
    "GetterMethodCouldBeProperty",
    "GroovyLangImmutable",
    "HardCodedWindowsFileSeparator",
    "HardCodedWindowsRootDirectory",
    "HashtableIsObsolete",
    "IfStatementBraces",
    "IfStatementCouldBeTernary",
    "IllegalClassMember",
    "IllegalClassReference",
    "IllegalPackageReference",
    "IllegalRegex",
    "ImplementationAsType",
    "ImportFromSamePackage",
    "ImportFromSunPackages",
    "InconsistentPropertyLocking",
    "InconsistentPropertySynchronization",
    "InsecureRandom",
    "IntegerGetInteger",
    "InterfaceName",
    "InvertedIfElse",
    "JUnitAssertAlwaysFails",
    "JUnitAssertAlwaysSucceeds",
    "JUnitAssertEqualsConstantActualValue",
    "JUnitFailWithoutMessage",
    "JUnitLostTest",
    "JUnitPublicField",
    "JUnitPublicNonTestMethod",
    "JUnitSetUpCallsSuper",
    "JUnitStyleAssertions",
    "JUnitTearDownCallsSuper",
    "JUnitTestMethodWithoutAssert",
    "JUnitUnnecessarySetUp",
    "JUnitUnnecessaryTearDown",
    "JUnitUnnecessaryThrowsException",
    "JdbcConnectionReference",
    "JdbcResultSetReference",
    "JdbcStatementReference",
    "LoggerForDifferentClass",
    "LoggerWithWrongModifiers",
    "LoggingSwallowsStacktrace",
    "LongLiteralWithLowerCaseL",
    "MethodCount",
    "MethodName",
    "MethodSize",
    "MisorderedStaticImports",
    "MissingNewInThrowStatement",
    "MultipleLoggers",
    "NestedBlockDepth",
    "NestedSynchronization",
    "NonFinalPublicField",
    "NonFinalSubclassOfSensitiveInterface",
    "ObjectFinalize",
    "ObjectOverrideMisspelledMethodName",
    "PackageName",
    "ParameterName",
    "ParameterReassignment",
    "PrintStackTrace",
    "Println",
    "PrivateFieldCouldBeFinal",
    "PropertyName",
    "PublicFinalizeMethod",
    "PublicInstanceField",
    "RandomDoubleCoercedToZero",
    "RemoveAllOnSelf",
    "RequiredRegex",
    "RequiredString",
    "ReturnFromFinallyBlock",
    "ReturnNullFromCatchBlock",
    "ReturnsNullInsteadOfEmptyArray",
    "ReturnsNullInsteadOfEmptyCollection",
    "SerialPersistentFields",
    "SerialVersionUID",
    "SimpleDateFormatMissingLocale",
    "SpaceAfterCatch",
    "SpaceAfterClosingBrace",
    "SpaceAfterComma",
    "SpaceAfterFor",
    "SpaceAfterIf",
    "SpaceAfterOpeningBrace",
    "SpaceAfterSemicolon",
    "SpaceAfterSwitch",
    "SpaceAfterWhile",
    "SpaceAroundClosureArrow",
    "SpaceAroundOperator",
    "SpaceBeforeClosingBrace",
    "SpaceBeforeOpeningBrace",
    "SpockIgnoreRestUsed",
    "StatelessClass",
    "StatelessSingleton",
    "StaticCalendarField",
    "StaticConnection",
    "StaticDateFormatField",
    "StaticMatcherField",
    "StaticSimpleDateFormatField",
    "SwallowThreadDeath",
    "SynchronizedMethod",
    "SynchronizedOnBoxedPrimitive",
    "SynchronizedOnGetClass",
    "SynchronizedOnReentrantLock",
    "SynchronizedOnString",
    "SynchronizedOnThis",
    "SynchronizedReadObjectMethod",
    "SystemErrPrint",
    "SystemExit",
    "SystemOutPrint",
    "SystemRunFinalizersOnExit",
    "TernaryCouldBeElvis",
    "ThisReferenceEscapesConstructor",
    "ThreadGroup",
    "ThreadLocalNotStaticFinal",
    "ThreadYield",
    "ThrowError",
    "ThrowException",
    "ThrowExceptionFromFinallyBlock",
    "ThrowNullPointerException",
    "ThrowRuntimeException",
    "ThrowThrowable",
    "UnnecessaryBigDecimalInstantiation",
    "UnnecessaryBigIntegerInstantiation",
    "UnnecessaryBooleanExpression",
    "UnnecessaryBooleanInstantiation",
    "UnnecessaryCallForLastElement",
    "UnnecessaryCallToSubstring",
    "UnnecessaryCatchBlock",
    "UnnecessaryCollectCall",
    "UnnecessaryCollectionCall",
    "UnnecessaryConstructor",
    "UnnecessaryDefInFieldDeclaration",
    "UnnecessaryDefInMethodDeclaration",
    "UnnecessaryDefInVariableDeclaration",
    "UnnecessaryDotClass",
    "UnnecessaryDoubleInstantiation",
    "UnnecessaryFail",
    "UnnecessaryFinalOnPrivateMethod",
    "UnnecessaryFloatInstantiation",
    "UnnecessaryGetter",
    "UnnecessaryGroovyImport",
    "UnnecessaryIfStatement",
    "UnnecessaryInstanceOfCheck",
    "UnnecessaryInstantiationToGetClass",
    "UnnecessaryIntegerInstantiation",
    "UnnecessaryLongInstantiation",
    "UnnecessaryModOne",
    "UnnecessaryNullCheck",
    "UnnecessaryNullCheckBeforeInstanceOf",
    "UnnecessaryObjectReferences",
    "UnnecessaryOverridingMethod",
    "UnnecessaryPackageReference",
    "UnnecessaryParenthesesForMethodCallWithClosure",
    "UnnecessaryPublicModifier",
    "UnnecessarySelfAssignment",
    "UnnecessarySemicolon",
    "UnnecessaryStringInstantiation",
    "UnnecessarySubstring",
    "UnnecessaryTernaryExpression",
    "UnnecessaryTransientModifier",
    "UnsafeArrayDeclaration",
    "UnsafeImplementationAsMap",
    "UnusedArray",
    "UnusedImport",
    "UnusedMethodParameter",
    "UnusedObject",
    "UnusedPrivateField",
    "UnusedPrivateMethod",
    "UnusedPrivateMethodParameter",
    "UnusedVariable",
    "UseAssertEqualsInsteadOfAssertTrue",
    "UseAssertFalseInsteadOfNegation",
    "UseAssertNullInsteadOfAssertEquals",
    "UseAssertSameInsteadOfAssertTrue",
    "UseAssertTrueInsteadOfAssertEquals",
    "UseAssertTrueInsteadOfNegation",
    "UseCollectMany",
    "UseCollectNested",
    "UseOfNotifyMethod",
    "VariableName",
    "VectorIsObsolete",
    "VolatileArrayField",
    "VolatileLongOrDoubleField",
    "WaitOutsideOfWhileLoop",
    "WhileStatementBrace"
  )
}
