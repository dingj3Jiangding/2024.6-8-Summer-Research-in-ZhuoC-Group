file://<HOME>/Learning%20Material/2024.6-8-Summer-Research-in-ZhuoC-Group/ApproxSquare/src/main/scala/ImplementPkg/exp_prcss.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/Library/Caches/Coursier/v1/https/maven.aliyun.com/repository/central/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/maven.aliyun.com/repository/central/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 506
uri: file://<HOME>/Learning%20Material/2024.6-8-Summer-Research-in-ZhuoC-Group/ApproxSquare/src/main/scala/ImplementPkg/exp_prcss.scala
text:
```scala
package ImplementPkg

// In this module, we should first turn the exp bit into real exponential
// Then add them up, add 127 [the offset] to formalize the result.

import chisel3._
import chisel3.util._

class exp_prcss(size: Int = 8) extends Module{
	val exp_io = IO (new Bundle{
		val x_exp = Input(UInt(size.W))
		val y_exp = Input(UInt(size.W))
		val out_exp = Output(UInt(size.W))
	})
	when (exp_io.x_exp === 0.U){
		exp_io.x_exp := 1.U(size.W)
	}
	when (exp_io.y_exp === 0.U){
		exp_io.y_exp := 1.U()@@
	}
	exp_io.out_exp := exp_io.x_exp + exp_io.y_exp - 127.U
}  

```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:131)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:186)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:94)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:63)
	scala.meta.internal.pc.MetalsSignatures$.signatures(MetalsSignatures.scala:17)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:51)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:426)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 0