$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/scenarios/admin.feature");
formatter.feature({
  "name": "Testing CRUD operations for the users API",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "There a User server and I am logged as admin",
  "keyword": "Given "
});
formatter.match({
  "location": "AdminStepDefs.thereAUserServerAndIAmLoggedAsAdmin()"
});
formatter.result({
  "error_message": "java.net.SocketTimeoutException: timeout\n\tat okio.SocketAsyncTimeout.newTimeoutException(Okio.kt:159)\n\tat okio.AsyncTimeout.exit$jvm(AsyncTimeout.kt:203)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:163)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.AdminStepDefs.thereAUserServerAndIAmLoggedAsAdmin(AdminStepDefs.java:32)\n\tat ✽.There a User server and I am logged as admin(file:src/test/resources/scenarios/admin.feature:4)\nCaused by: java.net.SocketException: Socket closed\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:183)\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:140)\n\tat okio.InputStreamSource.read(Okio.kt:102)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:159)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.AdminStepDefs.thereAUserServerAndIAmLoggedAsAdmin(AdminStepDefs.java:32)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:566)\n\tat cucumber.runtime.Utils$1.call(Utils.java:26)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:20)\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:57)\n\tat cucumber.runner.PickleStepDefinitionMatch.runStep(PickleStepDefinitionMatch.java:50)\n\tat cucumber.runner.TestStep.executeStep(TestStep.java:65)\n\tat cucumber.runner.TestStep.run(TestStep.java:50)\n\tat cucumber.runner.PickleStepTestStep.run(PickleStepTestStep.java:43)\n\tat cucumber.runner.TestCase.run(TestCase.java:46)\n\tat cucumber.runner.Runner.runPickle(Runner.java:50)\n\tat io.cucumber.junit.PickleRunners$NoStepDescriptions.run(PickleRunners.java:146)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:68)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:23)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:144)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:65)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat io.cucumber.junit.Cucumber$RunCucumber.evaluate(Cucumber.java:174)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\n\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)\n\tat com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)\n\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)\n\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)\n",
  "status": "failed"
});
formatter.step({
  "name": "There is another user to block with id \"celinedion@heigvd.ch\"",
  "keyword": "And "
});
formatter.match({
  "location": "AdminStepDefs.thereIsAnotherUserToBlockWithId(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "name": "Block a user as an admin by making a PUT request",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I have the email \"celinedion@heigvd.ch\" of a user",
  "keyword": "Given "
});
formatter.match({
  "location": "AdminStepDefs.iHaveTheEmailOfAUser(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I \"PUT\" to the /users/block/\"email\" endpoint",
  "keyword": "When "
});
formatter.match({
  "location": "AdminStepDefs.iToTheUsersBlockEndpoint(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I receive a 200 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "AuthStepdefs.iReceiveAStatusCode(int)"
});
formatter.result({
  "status": "skipped"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "There a User server and I am logged as admin",
  "keyword": "Given "
});
formatter.match({
  "location": "AdminStepDefs.thereAUserServerAndIAmLoggedAsAdmin()"
});
formatter.result({
  "error_message": "java.net.SocketTimeoutException: timeout\n\tat okio.SocketAsyncTimeout.newTimeoutException(Okio.kt:159)\n\tat okio.AsyncTimeout.exit$jvm(AsyncTimeout.kt:203)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:163)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.AdminStepDefs.thereAUserServerAndIAmLoggedAsAdmin(AdminStepDefs.java:32)\n\tat ✽.There a User server and I am logged as admin(file:src/test/resources/scenarios/admin.feature:4)\nCaused by: java.net.SocketTimeoutException: Read timed out\n\tat java.base/java.net.SocketInputStream.socketRead0(Native Method)\n\tat java.base/java.net.SocketInputStream.socketRead(SocketInputStream.java:115)\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:168)\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:140)\n\tat okio.InputStreamSource.read(Okio.kt:102)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:159)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.AdminStepDefs.thereAUserServerAndIAmLoggedAsAdmin(AdminStepDefs.java:32)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:566)\n\tat cucumber.runtime.Utils$1.call(Utils.java:26)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:20)\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:57)\n\tat cucumber.runner.PickleStepDefinitionMatch.runStep(PickleStepDefinitionMatch.java:50)\n\tat cucumber.runner.TestStep.executeStep(TestStep.java:65)\n\tat cucumber.runner.TestStep.run(TestStep.java:50)\n\tat cucumber.runner.PickleStepTestStep.run(PickleStepTestStep.java:43)\n\tat cucumber.runner.TestCase.run(TestCase.java:46)\n\tat cucumber.runner.Runner.runPickle(Runner.java:50)\n\tat io.cucumber.junit.PickleRunners$NoStepDescriptions.run(PickleRunners.java:146)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:68)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:23)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:144)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:65)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat io.cucumber.junit.Cucumber$RunCucumber.evaluate(Cucumber.java:174)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\n\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)\n\tat com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)\n\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)\n\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)\n",
  "status": "failed"
});
formatter.step({
  "name": "There is another user to block with id \"celinedion@heigvd.ch\"",
  "keyword": "And "
});
formatter.match({
  "location": "AdminStepDefs.thereIsAnotherUserToBlockWithId(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "name": "Unblock a user as an admin by making a DELETE request",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I have the email of a user",
  "keyword": "Given "
});
formatter.match({
  "location": "AdminStepDefs.iHaveTheEmailOfAUser()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I \"DELETE\" to the /users/block/\"email\" endpoint",
  "keyword": "When "
});
formatter.match({
  "location": "AdminStepDefs.iToTheUsersBlockEndpoint(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I receive a 200 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "AuthStepdefs.iReceiveAStatusCode(int)"
});
formatter.result({
  "status": "skipped"
});
formatter.uri("file:src/test/resources/scenarios/auth.feature");
formatter.feature({
  "name": "Testing CRUD operations for the users API",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "There a User server and auth server",
  "keyword": "Given "
});
formatter.match({
  "location": "AuthStepdefs.thereAUserServerAndAuthServer()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Login to the server",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I have a the email \"dorianekaffo@gmail.com\" and a password \"administrator\"",
  "keyword": "Given "
});
formatter.match({
  "location": "AuthStepdefs.iHaveATheEmailAndAPassword(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I \"POST\" it to the /login endpoint",
  "keyword": "When "
});
formatter.match({
  "location": "AuthStepdefs.iItToTheLoginEndpoint(String)"
});
formatter.result({
  "error_message": "java.net.SocketTimeoutException: timeout\n\tat okio.SocketAsyncTimeout.newTimeoutException(Okio.kt:159)\n\tat okio.AsyncTimeout.exit$jvm(AsyncTimeout.kt:203)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:163)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.AuthStepdefs.iItToTheLoginEndpoint(AuthStepdefs.java:44)\n\tat ✽.I \"POST\" it to the /login endpoint(file:src/test/resources/scenarios/auth.feature:8)\nCaused by: java.net.SocketException: Socket closed\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:183)\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:140)\n\tat okio.InputStreamSource.read(Okio.kt:102)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:159)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.AuthStepdefs.iItToTheLoginEndpoint(AuthStepdefs.java:44)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:566)\n\tat cucumber.runtime.Utils$1.call(Utils.java:26)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:20)\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:57)\n\tat cucumber.runner.PickleStepDefinitionMatch.runStep(PickleStepDefinitionMatch.java:50)\n\tat cucumber.runner.TestStep.executeStep(TestStep.java:65)\n\tat cucumber.runner.TestStep.run(TestStep.java:50)\n\tat cucumber.runner.PickleStepTestStep.run(PickleStepTestStep.java:43)\n\tat cucumber.runner.TestCase.run(TestCase.java:46)\n\tat cucumber.runner.Runner.runPickle(Runner.java:50)\n\tat io.cucumber.junit.PickleRunners$NoStepDescriptions.run(PickleRunners.java:146)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:68)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:23)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:144)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:65)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat io.cucumber.junit.Cucumber$RunCucumber.evaluate(Cucumber.java:174)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\n\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)\n\tat com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)\n\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)\n\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)\n",
  "status": "failed"
});
formatter.step({
  "name": "I receive a 200 status code and a token",
  "keyword": "Then "
});
formatter.match({
  "location": "AuthStepdefs.iReceiveAStatusCodeAndAToken(int)"
});
formatter.result({
  "status": "skipped"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "There a User server and auth server",
  "keyword": "Given "
});
formatter.match({
  "location": "AuthStepdefs.thereAUserServerAndAuthServer()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Logout to the server",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I am logged to the system",
  "keyword": "Given "
});
formatter.match({
  "location": "AuthStepdefs.iAmLoggedToTheSystem()"
});
formatter.result({
  "error_message": "java.net.SocketTimeoutException: timeout\n\tat okio.SocketAsyncTimeout.newTimeoutException(Okio.kt:159)\n\tat okio.AsyncTimeout.exit$jvm(AsyncTimeout.kt:203)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:163)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.AuthStepdefs.iAmLoggedToTheSystem(AuthStepdefs.java:64)\n\tat ✽.I am logged to the system(file:src/test/resources/scenarios/auth.feature:12)\nCaused by: java.net.SocketException: Socket closed\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:183)\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:140)\n\tat okio.InputStreamSource.read(Okio.kt:102)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:159)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.AuthStepdefs.iAmLoggedToTheSystem(AuthStepdefs.java:64)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:566)\n\tat cucumber.runtime.Utils$1.call(Utils.java:26)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:20)\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:57)\n\tat cucumber.runner.PickleStepDefinitionMatch.runStep(PickleStepDefinitionMatch.java:50)\n\tat cucumber.runner.TestStep.executeStep(TestStep.java:65)\n\tat cucumber.runner.TestStep.run(TestStep.java:50)\n\tat cucumber.runner.PickleStepTestStep.run(PickleStepTestStep.java:43)\n\tat cucumber.runner.TestCase.run(TestCase.java:46)\n\tat cucumber.runner.Runner.runPickle(Runner.java:50)\n\tat io.cucumber.junit.PickleRunners$NoStepDescriptions.run(PickleRunners.java:146)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:68)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:23)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:144)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:65)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat io.cucumber.junit.Cucumber$RunCucumber.evaluate(Cucumber.java:174)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\n\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)\n\tat com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)\n\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)\n\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)\n",
  "status": "failed"
});
formatter.step({
  "name": "I \"POST\" it to the /logout endpoint",
  "keyword": "When "
});
formatter.match({
  "location": "AuthStepdefs.iItToTheLogoutEndpoint(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I receive a 200 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "AuthStepdefs.iReceiveAStatusCode(int)"
});
formatter.result({
  "status": "skipped"
});
formatter.uri("file:src/test/resources/scenarios/users.feature");
formatter.feature({
  "name": "Testing operation on the Users API",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "I am logged",
  "keyword": "Given "
});
formatter.match({
  "location": "UsersStepdefs.iAmLogged()"
});
formatter.result({
  "error_message": "java.net.SocketTimeoutException: timeout\n\tat okio.SocketAsyncTimeout.newTimeoutException(Okio.kt:159)\n\tat okio.AsyncTimeout.exit$jvm(AsyncTimeout.kt:203)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:163)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.UsersStepdefs.iAmLogged(UsersStepdefs.java:31)\n\tat ✽.I am logged(file:src/test/resources/scenarios/users.feature:4)\nCaused by: java.net.SocketException: Socket closed\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:183)\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:140)\n\tat okio.InputStreamSource.read(Okio.kt:102)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:159)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.UsersStepdefs.iAmLogged(UsersStepdefs.java:31)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:566)\n\tat cucumber.runtime.Utils$1.call(Utils.java:26)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:20)\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:57)\n\tat cucumber.runner.PickleStepDefinitionMatch.runStep(PickleStepDefinitionMatch.java:50)\n\tat cucumber.runner.TestStep.executeStep(TestStep.java:65)\n\tat cucumber.runner.TestStep.run(TestStep.java:50)\n\tat cucumber.runner.PickleStepTestStep.run(PickleStepTestStep.java:43)\n\tat cucumber.runner.TestCase.run(TestCase.java:46)\n\tat cucumber.runner.Runner.runPickle(Runner.java:50)\n\tat io.cucumber.junit.PickleRunners$NoStepDescriptions.run(PickleRunners.java:146)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:68)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:23)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:144)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:65)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat io.cucumber.junit.Cucumber$RunCucumber.evaluate(Cucumber.java:174)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\n\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)\n\tat com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)\n\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)\n\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)\n",
  "status": "failed"
});
formatter.scenario({
  "name": "Create a user using POST request",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I have a User payload",
  "keyword": "Given "
});
formatter.match({
  "location": "UsersStepdefs.iHaveAUserPayload()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I POST it to the /users endpoint",
  "keyword": "When "
});
formatter.match({
  "location": "UsersStepdefs.iPOSTItToTheUsersEndpoint()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "uI receive a 200 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "UsersStepdefs.iReceiveAStatusCode(int)"
});
formatter.result({
  "status": "skipped"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "I am logged",
  "keyword": "Given "
});
formatter.match({
  "location": "UsersStepdefs.iAmLogged()"
});
formatter.result({
  "error_message": "java.net.SocketTimeoutException: timeout\n\tat okio.SocketAsyncTimeout.newTimeoutException(Okio.kt:159)\n\tat okio.AsyncTimeout.exit$jvm(AsyncTimeout.kt:203)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:163)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.UsersStepdefs.iAmLogged(UsersStepdefs.java:31)\n\tat ✽.I am logged(file:src/test/resources/scenarios/users.feature:4)\nCaused by: java.net.SocketException: Socket closed\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:183)\n\tat java.base/java.net.SocketInputStream.read(SocketInputStream.java:140)\n\tat okio.InputStreamSource.read(Okio.kt:102)\n\tat okio.AsyncTimeout$source$1.read(AsyncTimeout.kt:159)\n\tat okio.RealBufferedSource.indexOf(RealBufferedSource.kt:349)\n\tat okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:222)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readHeaderLine(Http1ExchangeCodec.kt:210)\n\tat okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:181)\n\tat okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:105)\n\tat okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:37)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:82)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:84)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:71)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:112)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:87)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.kt:184)\n\tat okhttp3.RealCall.execute(RealCall.kt:66)\n\tat ch.heigvd.amt.p2.steps.UsersStepdefs.iAmLogged(UsersStepdefs.java:31)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:566)\n\tat cucumber.runtime.Utils$1.call(Utils.java:26)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:20)\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:57)\n\tat cucumber.runner.PickleStepDefinitionMatch.runStep(PickleStepDefinitionMatch.java:50)\n\tat cucumber.runner.TestStep.executeStep(TestStep.java:65)\n\tat cucumber.runner.TestStep.run(TestStep.java:50)\n\tat cucumber.runner.PickleStepTestStep.run(PickleStepTestStep.java:43)\n\tat cucumber.runner.TestCase.run(TestCase.java:46)\n\tat cucumber.runner.Runner.runPickle(Runner.java:50)\n\tat io.cucumber.junit.PickleRunners$NoStepDescriptions.run(PickleRunners.java:146)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:68)\n\tat io.cucumber.junit.FeatureRunner.runChild(FeatureRunner.java:23)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:144)\n\tat io.cucumber.junit.Cucumber.runChild(Cucumber.java:65)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat io.cucumber.junit.Cucumber$RunCucumber.evaluate(Cucumber.java:174)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\n\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)\n\tat com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)\n\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)\n\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)\n",
  "status": "failed"
});
formatter.scenario({
  "name": "Get a user using GET request",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I have a the email of a user",
  "keyword": "Given "
});
formatter.match({
  "location": "UsersStepdefs.iHaveATheEmailOfAUser()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I GET it to the /users/\"email\" endpoint",
  "keyword": "When "
});
formatter.match({
  "location": "UsersStepdefs.iGETItToTheUsersEndpoint(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I receive a 200 status code and a user payload",
  "keyword": "Then "
});
formatter.match({
  "location": "UsersStepdefs.iReceiveAStatusCodeAndAUserPayload(int)"
});
formatter.result({
  "status": "skipped"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "I am logged",
  "keyword": "Given "
});
formatter.match({
  "location": "UsersStepdefs.iAmLogged()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Update a user using PUT request",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I have a User payload",
  "keyword": "Given "
});
formatter.match({
  "location": "UsersStepdefs.iHaveAUserPayload()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I PUT it to the /users endpoint",
  "keyword": "When "
});
formatter.match({
  "location": "UsersStepdefs.iPUTItToTheUsersEndpoint()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "uI receive a 200 status code",
  "keyword": "Then "
});
formatter.match({
  "location": "UsersStepdefs.iReceiveAStatusCode(int)"
});
formatter.result({
  "error_message": "java.lang.AssertionError\n\tat org.junit.Assert.fail(Assert.java:86)\n\tat org.junit.Assert.assertTrue(Assert.java:41)\n\tat org.junit.Assert.assertTrue(Assert.java:52)\n\tat ch.heigvd.amt.p2.steps.UsersStepdefs.iReceiveAStatusCode(UsersStepdefs.java:121)\n\tat ✽.uI receive a 200 status code(file:src/test/resources/scenarios/users.feature:19)\n",
  "status": "failed"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "I am logged",
  "keyword": "Given "
});
formatter.match({
  "location": "UsersStepdefs.iAmLogged()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Delete a user using DELETE request",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I have a User payload",
  "keyword": "Given "
});
formatter.match({
  "location": "UsersStepdefs.iHaveAUserPayload()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I DELETE it to the /users/\"email\" endpoint",
  "keyword": "When "
});
formatter.match({
  "location": "UsersStepdefs.iDELETEItToTheUsersEndpoint(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I receive a 200 status code with a feedback",
  "keyword": "Then "
});
formatter.match({
  "location": "UsersStepdefs.iReceiveAStatusCodeWithAFeedback(int)"
});
formatter.result({
  "error_message": "java.lang.AssertionError\n\tat org.junit.Assert.fail(Assert.java:86)\n\tat org.junit.Assert.assertTrue(Assert.java:41)\n\tat org.junit.Assert.assertTrue(Assert.java:52)\n\tat ch.heigvd.amt.p2.steps.UsersStepdefs.iReceiveAStatusCodeWithAFeedback(UsersStepdefs.java:115)\n\tat ✽.I receive a 200 status code with a feedback(file:src/test/resources/scenarios/users.feature:24)\n",
  "status": "failed"
});
});